package com.example.validation;

import com.example.validation.annotation.HasUniqueConstraints;
import com.example.validation.annotation.UniqueConstraint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UniqueValidator implements ConstraintValidator<HasUniqueConstraints, Object>
{

    @PersistenceContext
    private EntityManager em;

    private Class<?>[] groups;

    private String message;

    @Override
    public void initialize(final HasUniqueConstraints constraintAnnotation)
    {
        this.groups = constraintAnnotation.groups();
        this.message = constraintAnnotation.message();
    }


    @Override
    public boolean isValid(final Object entity, final ConstraintValidatorContext constraintValidatorContext)
    {

        // In case this UniqueValidator has been instantiated by Hibernate (Hibernate validation ON by default)
        // the entity manager will be NULL so do not validate and return true
        if (em == null) return true;

        // TODO: check that object o is annotated @Entity
        Class<?> objectClass = entity.getClass();
        final List<Field> uniqueFields = new ArrayList<>();
        Field softDeleteField = null;
        while (objectClass != null && objectClass != Object.class)
        {
            Field[] fields = objectClass.getDeclaredFields();
            for (Field field : fields)
            {
                UniqueConstraint uniqueConstraint = field.getDeclaredAnnotation(UniqueConstraint.class);
                if (uniqueConstraint != null)
                {
                    if (uniqueConstraint.isSoftDeleteFlag())
                    {
                        // TODO: check the softdelete field is of type boolean or Boolean.
                        softDeleteField = field;
                    }
                    else
                    {
                        field.setAccessible(true);
                        uniqueFields.add(field);
                    }
                }
            }
            objectClass = objectClass.getSuperclass();
        }

        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<?> criteriaQuery = cb.createQuery(entity.getClass());

        final Root rootEntity = criteriaQuery.from(entity.getClass());
        final List<Predicate> predicates = new ArrayList<>(uniqueFields.size());
        for (final Field uniqueField : uniqueFields)
        {
            try
            {
                predicates.add(cb.equal(rootEntity.get(uniqueField.getName()), uniqueField.get(entity)));
            }
            catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }

        if (softDeleteField != null)
        {
            predicates.add(cb.notEqual(rootEntity.get(softDeleteField.getName()), true));
        }

        criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[]{}));
        List<?> results = em.createQuery(criteriaQuery).getResultList();

        // If the list is empty then @UniqueConstraint properties are indeed unique.
        return results.isEmpty();
    }
}

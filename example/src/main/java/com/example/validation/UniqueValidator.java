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

    private Class<?> entityClass;

    private String identityFieldName;

    private String softDeleteField;

    @Override
    public void initialize(final HasUniqueConstraints constraintAnnotation)
    {
        this.groups = constraintAnnotation.groups();
        this.message = constraintAnnotation.message();
        this.entityClass = constraintAnnotation.entityClass();
        this.softDeleteField = constraintAnnotation.softDeleteField();
        this.identityFieldName = constraintAnnotation.identityFieldName();
        if (entityClass != Void.class && identityFieldName.isEmpty())
        {
            throw new RuntimeException("Entity class defined without specify identity field name");
        }
    }


    @Override
    public boolean isValid(final Object dto, final ConstraintValidatorContext constraintValidatorContext)
    {

        // In case this UniqueValidator has been instantiated by Hibernate (Hibernate validation ON by default)
        // the entity manager will be NULL so do not validate and return true
        if (em == null) return true;

        // Check if the entity class has been manually specified;
        entityClass = entityClass == Void.class ? dto.getClass() : entityClass;


        // TODO: check that object o is annotated @Entity
        Class<?> dtoClass = dto.getClass();
        final List<Field> uniqueFields = new ArrayList<>();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<?> criteriaQuery = cb.createQuery(entityClass);
        final Root rootEntity = criteriaQuery.from(entityClass);
        Object existingEntity = null;

        // If identityFieldName has been specified then get the entity - this may be a PATCH request so need the entity
        // to ensure the combination of new fields with those old-but-unspecified 'unique' fields will remain unique after
        // the patch
        if (!identityFieldName.isEmpty())
        {
            try
            {
                CriteriaQuery<?> existingEntityQuery = cb.createQuery(entityClass);
                Root rootExistingEntity = existingEntityQuery.from(entityClass);
                final Field objectIdentityField = dto.getClass().getDeclaredField(identityFieldName);
                objectIdentityField.setAccessible(true);
                existingEntity = em.createQuery(existingEntityQuery
                        .select(rootExistingEntity)
                        .where(cb.equal(rootExistingEntity.get(identityFieldName), objectIdentityField.get(dto)))).getSingleResult();
            }
            catch (NoSuchFieldException | IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }


        final List<Predicate> predicates = new ArrayList<>(uniqueFields.size());
        while (dtoClass != null && dtoClass != Object.class)
        {
            Field[] fields = dtoClass.getDeclaredFields();
            for (Field field : fields)
            {
                UniqueConstraint uniqueConstraint = field.getDeclaredAnnotation(UniqueConstraint.class);
                if (uniqueConstraint != null)
                {
                    try
                    {
                        field.setAccessible(true);
                        // Check if the property name has been specified otherwise use the field name.
                        final String propertyName = uniqueConstraint.propertyName().isEmpty() ? field.getName() : uniqueConstraint.propertyName();
                        final Object newValue = field.get(dto);
                        if (existingEntity == null)
                        {
                            // This is not a patch update so can use the value directly from the object passed in.
                            predicates.add(cb.equal(rootEntity.get(propertyName), newValue));
                        }
                        else
                        {
                            // This is a PATCH update - the value may not have been specified. If not use the existing value
                            // otherwise use the new
                            if (newValue == null)
                            {
                                final Field entityField = entityClass.getDeclaredField(propertyName);
                                entityField.setAccessible(true);
                                predicates.add(cb.equal(rootEntity.get(propertyName), entityField.get(existingEntity)));
                            }
                            else
                            {
                                predicates.add(cb.equal(rootEntity.get(propertyName), newValue));
                            }
                        }
                    }
                    catch (IllegalAccessException | NoSuchFieldException e)
                    {
                        throw new RuntimeException(e);
                    }
                    uniqueFields.add(field);
                }
            }
            dtoClass = dtoClass.getSuperclass();
        }

        // Include soft delete field if specified
        if (!softDeleteField.isEmpty())
        {
            predicates.add(cb.notEqual(rootEntity.get(softDeleteField), true));
        }

        // TODO: Only need a count - modify to be more performant. Getting the whole object
        // may result in unnecessary queries.
        criteriaQuery.select(rootEntity).where(predicates.toArray(new Predicate[]{}));
        List<?> results = em.createQuery(criteriaQuery).getResultList();

        // If the list is empty then @UniqueConstraint properties are indeed unique.
        if ( ! results.isEmpty())
        {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Entity already exists for the specified properties")
            .addConstraintViolation();
        }
        return results.isEmpty();
    }
}

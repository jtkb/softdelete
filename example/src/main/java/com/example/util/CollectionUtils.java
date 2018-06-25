package com.example.util;

import com.example.SetForeignKey;
import com.example.entity.base.Deleted;
import com.example.entity.base.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class CollectionUtils
{
    private CollectionUtils()
    {
    }

    public static <E, I extends Serializable, R extends JpaRepository<E, I>>
    void updateCollections(Set<I> newIds, R repository, Function<E, I> foreignKey)
    {
        for (I id : newIds)
        {
            final E entity = repository.getOne(id);
            I foreignKeyId = foreignKey.apply(entity);
        }
    }

    public static <E1 extends Identity<I1> & Deleted,
            E2 extends Identity<I2> & Deleted,
            J extends Deleted & SetForeignKey<I1>, // should also extend SetForeignKey<>
            I1 extends Serializable, I2 extends Serializable,
            R1 extends JpaRepository<E1, I1>,
            R2 extends JpaRepository<E2, I2>>
    void updateCollections(I1 fromId, List<I2> newToIds, List<J> currentJoins, Class<J> joinClazz, R1 fromRepository, R2 toRepository,
                           Function<J, I1> getFkE1, Function<J, I2> getFk2, BiConsumer<J, I1> setFK1) throws IllegalAccessException, InstantiationException
    {
        for (final J joinRecord : currentJoins)
        {
            if (newToIds.contains(getFk2.apply(joinRecord)))
            {
                // Already exists - remove from new Id List
                newToIds.remove(getFk2.apply(joinRecord));
            }
            else
            {
                // Not in the list - soft delete.
                joinRecord.setDeleted(true);
            }
        }
        for (final I2 newToId : newToIds)
        {
            final J newJoin = joinClazz.newInstance();
            setFK1.setFk(fromId);
        }

        // return new Set<J>

    }
}

package com.example.util;

import com.example.entity.base.Deleted;
import com.example.entity.base.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public class CollectionUtils
{
    private CollectionUtils()
    {

    }

    public static <E1 extends Identity<I1> & Deleted, E2 extends Identity<I2> & Deleted,
            I1 extends Serializable, I2 extends Serializable,
            J extends Deleted,
            R1 extends JpaRepository<E1, I1>,
            R2 extends JpaRepository<E2, I2>> void updateJoinTable(E1 entityFrom, List<I2> idsTo, R1 fromRepository, R2 toRepository, Class<J> joinTableClazz)
    {

    }
}

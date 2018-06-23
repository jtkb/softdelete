package com.example.dao;

import java.util.List;

public interface DeletedDao<T, I>
{
    List<T> getAllDeleted();

    T getDeleted(I id);
}

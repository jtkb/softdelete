package com.example.entity.base;

import java.io.Serializable;

public interface Identity<I extends Serializable>
{
    I getId();

    void setId(I id);
}

package com.example;

@FunctionalInterface
public interface SetForeignKey<I>
{
    Void setFk(I fk);
}

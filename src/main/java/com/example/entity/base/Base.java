package com.example.entity.base;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Access(AccessType.FIELD)
public interface Base<T extends Serializable>
{

}

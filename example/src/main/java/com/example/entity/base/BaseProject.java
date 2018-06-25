package com.example.entity.base;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
@Table(name = "PROJECT")
public abstract class BaseProject<E extends BaseEmployee> implements Serializable, Identity<Long>, Deleted
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "proj_id"), inverseJoinColumns = @JoinColumn(name = "emp_id"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    private Set<E> employees;

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public Set<E> getEmployees()
    {
        return employees;
    }

    public void setEmployees(final Set<E> employees)
    {
        this.employees = employees;
    }
}

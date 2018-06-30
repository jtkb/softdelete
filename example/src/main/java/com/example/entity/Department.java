package com.example.entity;

import com.example.entity.base.Deleted;
import com.example.entity.base.Identity;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
public class Department implements Serializable, Identity<Long>, Deleted
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @Where(clause = "is_deleted = false")
    private Set<Employee> departmentMembers;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Override
    public Long getId()
    {
        return this.id;
    }

    @Override
    public void setId(final Long id)
    {
        this.id = id;
    }

    @Override
    public Boolean isDeleted()
    {
        return this.isDeleted;
    }

    @Override
    public void setDeleted(final Boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public Set<Employee> getDepartmentMembers()
    {
        return departmentMembers;
    }

    public void setDepartmentMembers(final Set<Employee> departmentMembers)
    {
        this.departmentMembers = departmentMembers;
    }
}

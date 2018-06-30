package com.example.entity;

import com.example.entity.base.Deleted;
import com.example.entity.base.Identity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable, Identity<Long>, Deleted
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "proj_id"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    @Where(clause = "is_deleted = false")
    private Set<Project> projects;

    @ManyToOne
    @Where(clause = "is_deleted = false")
    private Department department;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public void setId(final Long id)
    {
        this.id = id;
    }

    @Override
    public Boolean isDeleted()
    {
        return isDeleted;
    }

    @Override
    public void setDeleted(final Boolean deleted)
    {
        isDeleted = deleted;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public Set<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(final Set<Project> projects)
    {
        this.projects = projects;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment(final Department department)
    {
        this.department = department;
    }
}

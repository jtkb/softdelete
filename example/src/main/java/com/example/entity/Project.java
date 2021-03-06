package com.example.entity;

import com.example.entity.base.Deleted;
import com.example.entity.base.Identity;
import com.example.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PROJECT")
public class Project implements Serializable, Identity<Long>, Deleted
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Project.class, Views.Employee.class})
    private Long id;

    @Column(name = "name")
    @JsonView({Views.Project.class, Views.Employee.class})
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "proj_id"), inverseJoinColumns = @JoinColumn(name = "emp_id"))
    @Where(clause = "is_deleted = false")
    @JsonView(Views.Project.class)
    private Set<Employee> employees;

    @JsonIgnore
    @Column(name = "is_deleted")
    @JsonView(Views.Project.class)
    private Boolean isDeleted;

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

    public Set<Employee> getEmployees()
    {
        return employees;
    }

    public void setEmployees(final Set<Employee> employees)
    {
        this.employees = employees;
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
}

package com.example.entity;

import com.example.entity.base.Deleted;
import com.example.entity.base.Identity;
import com.example.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
public class Department implements Serializable, Identity<Long>, Deleted
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Employee.class, Views.Department.class})
    private Long id;

    @Column(name = "name")
    @JsonView({Views.Employee.class,Views.Department.class})
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @WhereJoinTable(clause = "is_deleted = false")
    @JoinTable(name = "DEPARTMENT_EMPLOYEE", joinColumns = {@JoinColumn(name = "department_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    @SQLDelete(sql = "UPDATE DEPARTMENT_EMPLOYEE SET is_deleted = true where department_id = ? and employee_id = ? and is_deleted = false")
    @JsonView(Views.Department.class)
    private Set<Employee> departmentMembers;

    @Column(name = "is_deleted", nullable = false)
    @JsonView(Views.Department.class)
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

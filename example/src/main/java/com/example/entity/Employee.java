package com.example.entity;

import com.example.entity.base.Deleted;
import com.example.entity.base.Identity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable//, Identity<Long>, Deleted
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @Where(clause = "is_deleted = false")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMP_QUAL", joinColumns = @JoinColumn(name = "EMPLOYEE_ID"), inverseJoinColumns = @JoinColumn(name = "QUALIFICATION_ID"))
    @WhereJoinTable(clause = "IS_DELETED = false")
    @SQLDelete(sql = "UPDATE `EMP_QUAL` SET IS_DELETED = true where EMPLOYEE_ID = ? and QUALIFICATION_ID = ? and IS_DELETED = False")
    private Set<Qualification> qualifications;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "proj_id"))
    @Where(clause = "is_deleted = false")
    private Set<Project> projects;

    @JsonIgnore
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    //@Override
    public Long getId()
    {
        return this.id;
    }

    //@Override
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

    public Set<Qualification> getQualifications()
    {
        return qualifications;
    }

    public void setQualifications(final Set<Qualification> qualifications)
    {
        this.qualifications = qualifications;
    }

    public Boolean isDeleted()
    {
        return isDeleted;
    }

    public void setDeleted(final Boolean deleted)
    {
        isDeleted = deleted;
    }

}

package com.example.entity;

import com.example.jsonview.Views;
import com.example.validation.annotation.HasUniqueConstraints;
import com.example.validation.annotation.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
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
@HasUniqueConstraints
public class Employee implements Serializable//, Identity<Long>, Deleted
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Employee.class, Views.Department.class, Views.Project.class})
    private Long id;

    @Column(name = "NAME")
    @UniqueConstraint
    @JsonView({Views.Employee.class, Views.Department.class, Views.Project.class})
    private String name;

    @Column(name = "SKILL")
    @UniqueConstraint
    @JsonView({Views.Employee.class, Views.Department.class, Views.Project.class})
    private String skill;

    @ManyToOne
    @JoinTable(name = "DEPARTMENT_EMPLOYEE", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "department_id"))
    @Where(clause = "is_deleted = false")
    @JsonView(Views.Employee.class)
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMP_QUAL", joinColumns = @JoinColumn(name = "EMPLOYEE_ID"), inverseJoinColumns = @JoinColumn(name = "QUALIFICATION_ID"))
    @WhereJoinTable(clause = "IS_DELETED = false")
    @SQLDelete(sql = "UPDATE `EMP_QUAL` SET IS_DELETED = true where EMPLOYEE_ID = ? and QUALIFICATION_ID = ? and IS_DELETED = False")
    @JsonView(Views.Employee.class)
    private Set<Qualification> qualifications;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "proj_id"))
    @Where(clause = "is_deleted = false")
    @JsonView(Views.Employee.class)
    private Set<Project> projects;

    @JsonIgnore
    @UniqueConstraint(isSoftDeleteFlag = true)
    @Column(name = "is_deleted", nullable = false)
    @JsonView(Views.Employee.class)
    private Boolean isDeleted = false;

    public Long getId()
    {
        return this.id;
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

    public String getSkill()
    {
        return skill;
    }

    public void setSkill(final String skill)
    {
        this.skill = skill;
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

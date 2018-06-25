package com.example.entity.base;

import com.example.entity.EmployeeProject;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@MappedSuperclass
public abstract class BaseEmployee<P extends BaseProject> implements Serializable, Identity<Long>, Deleted
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "proj_id"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    private Set<P> projects;

    @OneToMany(mappedBy = "employeeId", cascade = CascadeType.ALL)
    private List<EmployeeProject> employeeProjects;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public Boolean isDeleted()
    {
        return isDeleted;
    }

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

    public Set<P> getProjects()
    {
        return projects;
    }

    public void setProjects(final Set<P> projects)
    {
        this.projects = projects;
    }
}

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
import java.util.Set;

@MappedSuperclass
public abstract class BaseEmployee<P extends BaseProject>
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

    public Boolean getDeleted()
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

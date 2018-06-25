package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMP_PROJ")
public class EmployeeProject
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "employeeId")
    @ManyToOne
    private Long employeeId;

    @ManyToOne
    @Column(name = "projectId")
    private Long projectId;

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public Long getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(final Long employeeId)
    {
        this.employeeId = employeeId;
    }

    public Long getProjectId()
    {
        return projectId;
    }

    public void setProjectId(final Long projectId)
    {
        this.projectId = projectId;
    }
}

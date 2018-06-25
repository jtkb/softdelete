package com.example.dto;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class EmployeePatchDto
{
    @NotNull
    private Long id;

    private String name;

    private Set<Long> projectIds;

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

    public Set<Long> getProjectIds()
    {
        return projectIds;
    }

    public void setProjectIds(final Set<Long> projectIds)
    {
        this.projectIds = projectIds;
    }
}

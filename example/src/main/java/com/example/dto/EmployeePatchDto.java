package com.example.dto;

import com.example.entity.Employee;
import com.example.validation.annotation.HasUniqueConstraints;
import com.example.validation.annotation.UniqueConstraint;

import javax.validation.constraints.NotNull;

@HasUniqueConstraints(entityClass = Employee.class, softDeleteField = "isDeleted", identityFieldName = "id", message = "The specified (name, skill) is not unique")
public class EmployeePatchDto
{
    @NotNull
    private Long id;

    @UniqueConstraint
    private String name;

    @UniqueConstraint
    private String skill;

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

    public String getSkill()
    {
        return skill;
    }

    public void setSkill(final String skill)
    {
        this.skill = skill;
    }
}

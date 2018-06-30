package com.example.dto;

import java.util.List;

public class DepartmentPatchDto
{
    private Long id;

    private String name;

    private List<Long> memberIds;

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

    public List<Long> getMemberIds()
    {
        return memberIds;
    }

    public void setMemberIds(final List<Long> memberIds)
    {
        this.memberIds = memberIds;
    }
}

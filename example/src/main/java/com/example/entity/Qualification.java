package com.example.entity;

import com.example.entity.base.Identity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUALIFICATION")
public class Qualification implements Identity<Long>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
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

    public Boolean getDeleted()
    {
        return isDeleted;
    }

    public void setDeleted(final Boolean deleted)
    {
        isDeleted = deleted;
    }
}

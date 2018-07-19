package com.example.entity;

import com.example.entity.base.Identity;
import com.example.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

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
    @JsonView({Views.Employee.class, Views.Qualification.class})
    private Long id;

    @Column(name = "NAME")
    @JsonView({Views.Employee.class, Views.Qualification.class})
    private String name;

    @Column(name = "IS_DELETED")
    @JsonView(Views.Qualification.class)
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

package com.example.entity;

import com.example.entity.base.BaseProject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class DeletedProject extends BaseProject<DeletedEmployee>
{
}

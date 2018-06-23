package com.example.entity;

import com.example.entity.base.BaseEmployee;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Where(clause = "is_deleted = false")
@Table(name = "EMPLOYEE")
public class Employee extends BaseEmployee<Project>
{
}

package com.example.entity;

import com.example.entity.base.BaseEmployee;
import org.hibernate.annotations.Where;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
@Where(clause = "is_deleted = true")
public class DeletedEmployee extends BaseEmployee<DeletedProject>
{

}

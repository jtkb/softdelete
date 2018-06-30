package com.example.dao;

import com.example.dto.DepartmentPatchDto;
import com.example.entity.Department;

import java.util.List;

public interface DepartmentDao
{
    Department postDepartment(Department department);

    Department getDepartment(Long id);

    List<Department> getAllDepartments();

    Department putDepartment(Department department);

    Department patchDepartment(DepartmentPatchDto patchDto);
}

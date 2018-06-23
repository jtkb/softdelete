package com.example.dao;

import com.example.entity.DeletedEmployee;
import com.example.entity.Employee;
import com.example.entity.base.BaseEmployee;

import java.util.List;

public interface EmployeeDao<E extends BaseEmployee>
{
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployees(List<Long> employeeIds);

    Employee getEmployee(Long id);

    List<DeletedEmployee> getAllDeletedEmployees();

    DeletedEmployee getDeletedEmployee(Long id);

    Integer deleteEmployee(List<Long> id);
}

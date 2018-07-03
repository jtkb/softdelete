package com.example.dao;

import com.example.entity.Employee;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EmployeeDao
{
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployees(List<Long> employeeIds);

    Employee getEmployee(Long id);

    Integer deleteEmployee(List<Long> id);

    Long deleteEmployeeDepartment(@NotNull List<Long> id);
}

package com.example.dao;

import com.example.dto.EmployeePatchDto;
import com.example.entity.Employee;

import java.util.List;

public interface EmployeeDao
{
    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployees(List<Long> employeeIds);

    Employee getEmployee(Long id);

    Integer deleteEmployee(List<Long> id);

    Long deleteEmployeeDepartment(List<Long> id);

    Employee patchEmployee(EmployeePatchDto employeePatchDto);

}

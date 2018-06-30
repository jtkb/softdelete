package com.example.dao;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeDaoImpl implements EmployeeDao
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee createEmployee(final Employee employee)
    {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee getEmployee(final Long id)
    {
        return employeeRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees(final List<Long> employeeIds)
    {
        return employeeRepository.findAllById(employeeIds);
    }

    @Override
    @Transactional
    public Integer deleteEmployee(final List<Long> ids)
    {
        return this.employeeRepository.softDelete(ids);
    }
}

package com.example.dao;

import com.example.entity.DeletedEmployee;
import com.example.entity.Employee;
import com.example.repository.DeletedEmployeeRepository;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeDaoImpl implements EmployeeDao<Employee>
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DeletedEmployeeRepository deletedEmployeeRepository;

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
    public List<DeletedEmployee> getAllDeletedEmployees()
    {
        return deletedEmployeeRepository.findAll();
    }

    @Override
    @Transactional
    public DeletedEmployee getDeletedEmployee(final Long id)
    {
        return deletedEmployeeRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Integer deleteEmployee(final List<Long> ids)
    {
        return this.employeeRepository.softDelete(ids);
    }
}

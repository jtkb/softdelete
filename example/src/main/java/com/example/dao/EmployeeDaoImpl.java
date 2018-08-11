package com.example.dao;

import com.example.dto.EmployeePatchDto;
import com.example.entity.Employee;
import com.example.repository.DepartmentEmployeeRepository;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class EmployeeDaoImpl implements EmployeeDao
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Override
    @Transactional
    public Employee createEmployee(final Employee employee)
    {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(final Employee employee)
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

    @Override
    @Transactional
    public Long deleteEmployeeDepartment(@NotNull final List<Long> ids)
    {
        Long count = 0L;
        for (final Long id : ids)
        {
            departmentEmployeeRepository.deleteById(id);
            count++;
        }

        return count;
    }

    @Override
    @Transactional
    public Employee patchEmployee(final EmployeePatchDto employeePatchDto)
    {
        final Employee employee = employeeRepository.getOne(employeePatchDto.getId());
        boolean isModified = false;
        if (employeePatchDto.getName() != null)
        {
            employee.setName(employeePatchDto.getName());
            isModified = true;
        }
        if (employeePatchDto.getSkill() != null)
        {
            employee.setSkill(employeePatchDto.getSkill());
            isModified = true;
        }
        return isModified ? employeeRepository.save(employee) : employee;
    }
}

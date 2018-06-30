package com.example.dao;

import com.example.dto.DepartmentPatchDto;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class DepartmentDaoImpl implements DepartmentDao
{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Department postDepartment(final Department department)
    {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public Department getDepartment(final Long id)
    {
        return departmentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Department> getAllDepartments()
    {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Department putDepartment(final Department department)
    {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public Department patchDepartment(final DepartmentPatchDto patchDto)
    {
        boolean isModified = false;
        final Department department = departmentRepository.getOne(patchDto.getId());

        // Name changed
        if (patchDto.getName() != null)
        {
            //department.setName(patchDto.getName());
            //isModified = true;
        }

        // Members changed
        if (patchDto.getMemberIds() != null)
        {

            final Iterator<Employee> it = department.getDepartmentMembers().iterator();
            while (it.hasNext())
            {
                final Employee member = it.next();
                if (patchDto.getMemberIds().contains(member.getId()))
                {
                    // Department already has specified member
                    patchDto.getMemberIds().remove(member.getId());
                }
                else
                {
                    // Current member is no longer the new list of members.
                    department.getDepartmentMembers().remove(member);
                }
            }

            // Add remaining new member Ids
            if ( ! patchDto.getMemberIds().isEmpty())
            {
                department.getDepartmentMembers().addAll(employeeRepository.findAllById(patchDto.getMemberIds()));
            }
            isModified = true;
        }

        return isModified ? departmentRepository.save(department) : department;
    }
}

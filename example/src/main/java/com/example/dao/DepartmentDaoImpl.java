package com.example.dao;

import com.example.dto.DepartmentPatchDto;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.repository.DepartmentEmployeeRepository;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentDaoImpl implements DepartmentDao
{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    private MapperFacade mapper = new DefaultMapperFactory.Builder().build().getMapperFacade();

    @PostConstruct
    public void postConstruct()
    {

    }

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
        boolean removeAllEmployees = false;
        Department departmentNoMembers = null;
        Department department = departmentRepository.getOne(patchDto.getId());

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
            final Set<Employee> employeesPendingDeletion = new HashSet<>();
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
                    employeesPendingDeletion.add(member);
                }
            }

            if (department.getDepartmentMembers().size()  == employeesPendingDeletion.size() && patchDto.getMemberIds().isEmpty())
            {
                // Completely emptied the list - this will cause Hibernate to HARD delete ALL join records which
                // have a department_id of the current department!
                List<Long> memberIds = employeesPendingDeletion.stream().map(Employee::getId).collect(Collectors.toList());
                departmentEmployeeRepository.softDeleteRecords(department.getId(), memberIds);
                removeAllEmployees = true;
            }
            else
            {
                department.getDepartmentMembers().removeAll(employeesPendingDeletion);
            }

            // Add remaining new member Ids
            if ( ! patchDto.getMemberIds().isEmpty())
            {
                department.getDepartmentMembers().addAll(employeeRepository.findAllById(patchDto.getMemberIds()));
            }
            isModified = true;
        }

        department = isModified ? departmentRepository.save(department) : department;
        if (removeAllEmployees)
        {
            // Hibernate will even do a hard delete if the the collection is removed at this point i.e. after the above save.
            // The reason for this is unknown but would appear to be odd especially as the save has already been called.
            departmentNoMembers = new Department();
            mapper.map(department, departmentNoMembers);
            departmentNoMembers.getDepartmentMembers().clear();
        }
        return removeAllEmployees ? departmentNoMembers : department;
    }
}

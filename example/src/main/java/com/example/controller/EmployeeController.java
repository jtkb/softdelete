package com.example.controller;

import com.example.dao.DeletedDao;
import com.example.dao.EmployeeDao;
import com.example.dao.ProjectDao;
import com.example.entity.DeletedEmployee;
import com.example.entity.Employee;
import com.example.entity.Project;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class EmployeeController
{
    @Autowired
    private EmployeeDao<Employee> employeeDao;

    @Autowired
    private DeletedDao<DeletedEmployee, Long> deleteEmployeeDao;

    @Autowired
    private ProjectDao<Project> projectDao;

    @ResponseBody
    @RequestMapping(path = "/employees", method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody @NotNull Employee employee)
    {
        return this.employeeDao.createEmployee(employee);
    }

    @ResponseBody
    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    @ApiOperation(value = "Get all employees.")
    public List<Employee> getAllEmployees()
    {
        return this.employeeDao.getAllEmployees();
    }

    @ResponseBody
    @RequestMapping(path = "/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable @NotNull final Long id)
    {
        return this.employeeDao.getEmployee(id);
    }

    @ResponseBody
    @RequestMapping(path = "/employees/deleted", method = RequestMethod.GET)
    public List<DeletedEmployee> getAllDeletedEmployees()
    {
        return this.employeeDao.getAllDeletedEmployees();
    }

    @ResponseBody
    @RequestMapping(path = "/employees/{id}", method = RequestMethod.DELETE)
    public Integer deleteUser(@PathVariable(name = "id") @NotNull final List<Long> id)
    {
        return employeeDao.deleteEmployee(id);
    }

}

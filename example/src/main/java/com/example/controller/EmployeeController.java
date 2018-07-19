package com.example.controller;

import com.example.dao.EmployeeDao;
import com.example.dao.ProjectDao;
import com.example.entity.Employee;
import com.example.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    private EmployeeDao employeeDao;

    @Autowired
    private ProjectDao projectDao;

    @ResponseBody
    @RequestMapping(path = "/employees", method = RequestMethod.POST)
    @JsonView(Views.Employee.class)
    public Employee createEmployee(@RequestBody @NotNull Employee employee)
    {
        return this.employeeDao.createEmployee(employee);
    }

    @ResponseBody
    @RequestMapping(path = "/employees/{id}", method = RequestMethod.PUT)
    @JsonView(Views.Employee.class)
    public Employee putEmployee(@PathVariable(name = "id") @NotNull final Long id, @RequestBody @NotNull final Employee employee)
    {
        return this.employeeDao.updateEmployee(employee);
    }

    @ResponseBody
    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    @ApiOperation(value = "Get all employees.")
    @JsonView(Views.Employee.class)
    public List<Employee> getAllEmployees()
    {
        return this.employeeDao.getAllEmployees();
    }

    @ResponseBody
    @RequestMapping(path = "/employees/{id}", method = RequestMethod.GET)
    @JsonView(Views.Employee.class)
    public Employee getEmployee(@PathVariable @NotNull final Long id)
    {
        return this.employeeDao.getEmployee(id);
    }

    @ResponseBody
    @RequestMapping(path = "/employees/{id}", method = RequestMethod.DELETE)
    @JsonView(Views.Employee.class)
    public Integer deleteUser(@PathVariable(name = "id") @NotNull final List<Long> id)
    {
        return employeeDao.deleteEmployee(id);
    }

    @ResponseBody
    @RequestMapping(path = "/employeedepartment", method = RequestMethod.DELETE)
    @JsonView(Views.Employee.class)
    public Long deleteEmployeeDepartment(@RequestBody @NotNull final List<Long> id)
    {
            return employeeDao.deleteEmployeeDepartment(id);
    }

}

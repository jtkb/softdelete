package com.example.controller;

import com.example.dao.DepartmentDao;
import com.example.dto.DepartmentPatchDto;
import com.example.entity.Department;
import com.example.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
public class DepartmentController
{
    @Autowired
    private DepartmentDao departmentDao;

    @ResponseBody
    @RequestMapping(path = "/departments", method = RequestMethod.POST)
    @JsonView(Views.Department.class)
    public Department postDepartment(@RequestBody @NotNull Department department)
    {
        return departmentDao.postDepartment(department);
    }
 
    @ResponseBody
    @RequestMapping(path = "/departments/{id}", method = RequestMethod.GET)
    @JsonView(Views.Department.class)
    public Department getDepartment(@PathVariable(name = "id") @NotNull final Long id)
    {
        return departmentDao.getDepartment(id);
    }
    
    @ResponseBody
    @RequestMapping(path = "/departments", method = RequestMethod.GET)
    @JsonView(Views.Department.class)
    public List<Department> getAllDepartments()
    {
        return departmentDao.getAllDepartments();
    }
    
    @ResponseBody
    @RequestMapping(path = "/departments/{id}", method = RequestMethod.PUT)
    @JsonView(Views.Department.class)
    public Department putDepartment(@PathVariable(name = "id") @NotNull final Long id, @RequestBody @NotNull final Department department)
    {
        return departmentDao.putDepartment(department);
    }

    @ResponseBody
    @RequestMapping(path = "/departments/{id}", method = RequestMethod.PATCH)
    @JsonView(Views.Department.class)
    public Department patchDepartment(@PathVariable(name = "id") @NotNull final Long id, @RequestBody @NotNull final DepartmentPatchDto patchDto)
    {
        return departmentDao.patchDepartment(patchDto);
    }
}

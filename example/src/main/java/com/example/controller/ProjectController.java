package com.example.controller;

import com.example.dao.EmployeeDao;
import com.example.dao.ProjectDao;
import com.example.entity.Employee;
import com.example.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;

@RestController
public class ProjectController
{
    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private EmployeeDao employeeDao;

    @ResponseBody
    @RequestMapping(path = "/projects", method = RequestMethod.POST)
    public Project createProject(@RequestBody @NotNull Project project)
    {
        return this.projectDao.createProject(project);
    }

    @ResponseBody
    @RequestMapping(path = "/projects", method = RequestMethod.GET)
    public List<Project> getAllProjects()
    {
        return this.projectDao.getProjects();
    }

    @ResponseBody
    @RequestMapping(path = "/projects/{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable @NotNull final Long id)
    {
        return this.projectDao.getProject(id);
    }

    @ResponseBody
    @RequestMapping(path = "/projects/{id}/employees", method = RequestMethod.PUT)
    public Project setEmployees(@PathVariable(name = "id") @NotNull final Long projectId, @RequestBody @NotNull final List<Long> employeeIds)
    {
        final List<Employee> employees = this.employeeDao.getAllEmployees(employeeIds);
        final Project project = this.projectDao.getProject(projectId);
        project.setEmployees(new HashSet<>(employees));
        //employees.forEach(employee -> employee.getProjects().add(project));

        return this.projectDao.updateProject(project);
    }

}

package com.example.controller;

import com.example.dao.QualificationDao;
import com.example.entity.Qualification;
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
public class QualificationController
{
    @Autowired
    private QualificationDao qualificationDao;

    @ResponseBody
    @RequestMapping(path = "/qualifications", method = RequestMethod.POST)
    @JsonView(Views.Qualification.class)
    Qualification postQualification(@RequestBody @NotNull final Qualification qualification)
    {
        return this.qualificationDao.createQualification(qualification);
    }

    @ResponseBody
    @RequestMapping(path = "/qualifications/{id}", method = RequestMethod.GET)
    @JsonView(Views.Qualification.class)
    Qualification getQualification(@PathVariable(name = "id") @NotNull final Long id)
    {
        return this.qualificationDao.getQualification(id);
    }

    @ResponseBody
    @RequestMapping(path = "/qualifications", method = RequestMethod.GET)
    @JsonView(Views.Qualification.class)
    List<Qualification> getAllQualifications()
    {
        return this.qualificationDao.getQualifications();
    }

    @ResponseBody
    @RequestMapping(path = "/qualifications/{id}", method = RequestMethod.PUT)
    @JsonView(Views.Qualification.class)
    Qualification putQualification(@PathVariable(name = "id") @NotNull final Long id, @RequestBody @NotNull final Qualification qualification)
    {
        return this.qualificationDao.updateQualification(qualification);
    }
}

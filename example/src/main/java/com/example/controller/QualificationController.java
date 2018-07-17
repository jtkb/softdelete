package com.example.controller;

import com.example.dao.QualificationDao;
import com.example.entity.Qualification;
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
    Qualification postQualification(@RequestBody @NotNull final Qualification qualification)
    {
        return this.qualificationDao.createQualification(qualification);
    }

    @ResponseBody
    @RequestMapping(path = "/qualifications/{id}", method = RequestMethod.GET)
    Qualification getQualification(@PathVariable(name = "id") @NotNull final Long id)
    {
        return this.qualificationDao.getQualification(id);
    }

    @ResponseBody
    @RequestMapping(path = "/qualifications", method = RequestMethod.GET)
    List<Qualification> getAllQualifications()
    {
        return this.qualificationDao.getQualifications();
    }

    @ResponseBody
    @RequestMapping(path = "/qualifications/{id}", method = RequestMethod.PUT)
    Qualification putQualification(@PathVariable(name = "id") @NotNull final Long id, @RequestBody @NotNull final Qualification qualification)
    {
        return this.qualificationDao.updateQualification(qualification);
    }
}

package com.example.dao;

import com.example.entity.Qualification;

import java.util.List;

public interface QualificationDao
{
    Qualification getQualification(Long id);

    List<Qualification> getQualifications();

    Qualification createQualification(Qualification qualification);

    Qualification updateQualification(Qualification qualification);
}

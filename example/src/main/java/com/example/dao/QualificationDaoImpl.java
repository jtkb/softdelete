package com.example.dao;

import com.example.entity.Qualification;
import com.example.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QualificationDaoImpl implements QualificationDao
{
    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    @Transactional
    public Qualification getQualification(final Long id)
    {
        return this.qualificationRepository.getOne(id);
    }

    @Override
    @Transactional
    public List<Qualification> getQualifications()
    {
        return this.qualificationRepository.findAll();
    }

    @Override
    @Transactional
    public Qualification createQualification(final Qualification qualification)
    {
        return this.qualificationRepository.save(qualification);
    }

    @Override
    @Transactional
    public Qualification updateQualification(final Qualification qualification)
    {
        return this.qualificationRepository.save(qualification);
    }
}

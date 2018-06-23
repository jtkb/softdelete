package com.example.dao;

import com.example.entity.DeletedEmployee;
import com.example.repository.DeletedEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeletedEmployeeDaoImpl implements DeletedDao<DeletedEmployee, Long>
{
    @Autowired
    private DeletedEmployeeRepository deletedEmployeeRepository;

    @Override
    @Transactional
    public List<DeletedEmployee> getAllDeleted()
    {
        return this.deletedEmployeeRepository.findAll();
    }

    @Override
    @Transactional
    public DeletedEmployee getDeleted(final Long id)
    {
        return this.deletedEmployeeRepository.findById(id).get();
    }
}

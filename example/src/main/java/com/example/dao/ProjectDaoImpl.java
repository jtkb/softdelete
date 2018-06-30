package com.example.dao;

import com.example.entity.Project;
import com.example.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectDaoImpl implements ProjectDao
{
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public Project createProject(final Project project)
    {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public List<Project> getProjects()
    {
        return projectRepository.findAll();
    }

    @Override
    @Transactional
    public Project getProject(final Long id)
    {
        return projectRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Project updateProject(final Project project)
    {
        return projectRepository.save(project);
    }
}

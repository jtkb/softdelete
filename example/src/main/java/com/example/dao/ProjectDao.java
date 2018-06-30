package com.example.dao;

import com.example.entity.Project;

import java.util.List;

public interface ProjectDao
{
    Project createProject(Project project);

    List<Project> getProjects();

    Project getProject(Long id);

    Project updateProject(Project project);
}

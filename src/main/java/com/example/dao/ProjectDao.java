package com.example.dao;

import com.example.entity.base.BaseProject;

import java.util.List;

public interface ProjectDao<T extends BaseProject>
{
    T createProject(T project);

    List<T> getProjects();

    T getProject(Long id);

    T updateProject(T project);
}

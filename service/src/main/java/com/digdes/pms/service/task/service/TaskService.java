package com.digdes.pms.service.task.service;

import com.digdes.pms.dto.project.ProjectDto;

import java.util.List;

public interface TaskService {
    boolean create(ProjectDto projectDto);

    boolean update(ProjectDto projectDto);

    ProjectDto findById(Long id);

    List<ProjectDto> findAll();

    boolean deleteById(Long id);
}

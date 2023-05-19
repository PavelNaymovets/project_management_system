package com.digdes.pms.service.project.service;

import com.digdes.pms.api.dto.project.ProjectDto;

import java.util.List;

public interface ProjectService {
    boolean create(ProjectDto projectDto);

    boolean update(ProjectDto projectDto);

    ProjectDto findById(Long id);

    List<ProjectDto> findAll();

    boolean deleteById(Long id);
}

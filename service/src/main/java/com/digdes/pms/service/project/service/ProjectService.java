package com.digdes.pms.service.project.service;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.dto.project.ProjectFilterDto;

import java.util.List;

public interface ProjectService {
    ProjectDto create(ProjectDto projectDto);

    ProjectDto update(ProjectDto projectDto);

    ProjectDto findById(Long id);

    List<ProjectDto> findAll();

    void updateStatus(Long id, String status);

    List<ProjectDto> findAllByFilter(ProjectFilterDto filter);
    ProjectDto deleteById(Long id);
}

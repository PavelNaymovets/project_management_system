package com.digdes.pms.service.project.service;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.model.project.Project;
import com.digdes.pms.repository.project.util.filter.ProjectFilter;

import java.util.List;

public interface ProjectService {
    boolean create(ProjectDto projectDto);

    boolean update(ProjectDto projectDto);

    ProjectDto findById(Long id);

    List<ProjectDto> findAll();

    boolean deleteById(Long id);

    List<ProjectDto> searchByFilter(ProjectFilter filter);
}

package com.digdes.pms.service.project.service;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.dto.project.ProjectFilterDto;
import com.digdes.pms.service.util.service.ServiceCRUD;
import com.digdes.pms.service.util.service.ServiceUpdateStatus;

import java.util.List;

public interface ProjectService extends ServiceCRUD<ProjectDto, ProjectFilterDto>, ServiceUpdateStatus {
    @Override
    ProjectDto create(ProjectDto projectDto);

    @Override
    ProjectDto update(ProjectDto projectDto);

    @Override
    ProjectDto deleteById(Long id);

    @Override
    ProjectDto findById(Long id);

    @Override
    List<ProjectDto> findAllByFilter(ProjectFilterDto filter);

    @Override
    void updateStatus(Long id, String status);
}

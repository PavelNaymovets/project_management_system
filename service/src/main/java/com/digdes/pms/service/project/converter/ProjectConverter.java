package com.digdes.pms.service.project.converter;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.model.project.Project;
import com.digdes.pms.service.util.converter.Converter;

public interface ProjectConverter extends Converter<Project, ProjectDto> {
    @Override
    Project convertToEntity(ProjectDto employeeDto);

    @Override
    ProjectDto convertToDto(Project employee);
}

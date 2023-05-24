package com.digdes.pms.service.project.converter;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.model.project.Project;

public interface ProjectConverter {
    Project convertToEntity(ProjectDto employeeDto);
    ProjectDto convertToDto(Project employee);
}

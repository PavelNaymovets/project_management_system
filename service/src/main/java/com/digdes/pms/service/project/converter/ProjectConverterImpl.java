package com.digdes.pms.service.project.converter;

import com.digdes.pms.api.dto.project.ProjectDto;
import com.digdes.pms.model.project.Project;

public class ProjectConverterImpl implements ProjectConverter{
    @Override
    public Project convertToEntity(ProjectDto employeeDto) {
        return Project.builder()
                .id(employeeDto.getId())
                .code(employeeDto.getCode())
                .name(employeeDto.getName())
                .description(employeeDto.getDescription())
                .status(employeeDto.getStatus())
                .build();
    }

    @Override
    public ProjectDto convertToDto(Project employee) {
        return ProjectDto.builder()
                .id(employee.getId())
                .code(employee.getCode())
                .name(employee.getName())
                .description(employee.getDescription())
                .status(employee.getStatus())
                .build();
    }
}

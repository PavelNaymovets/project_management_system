package com.digdes.pms.service.project.converter;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.model.project.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverterImpl implements ProjectConverter{
    @Override
    public Project convertToEntity(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .code(projectDto.getCode())
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .status(projectDto.getStatus())
                .build();
    }

    @Override
    public ProjectDto convertToDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .code(project.getCode())
                .name(project.getName())
                .description(project.getDescription())
                .status(project.getStatus())
                .build();
    }
}

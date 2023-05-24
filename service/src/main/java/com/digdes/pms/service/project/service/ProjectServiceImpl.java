package com.digdes.pms.service.project.service;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.model.project.Project;
import com.digdes.pms.repository.project.ProjectDao;
import com.digdes.pms.repository.project.ProjectRepository;
import com.digdes.pms.repository.project.util.filter.ProjectFilter;
import com.digdes.pms.service.project.converter.ProjectConverter;
import com.digdes.pms.service.project.converter.ProjectConverterImpl;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    public ProjectServiceImpl() {
        this.projectRepository = new ProjectDao();
        this.projectConverter = new ProjectConverterImpl();
    }

    @Override
    public boolean create(ProjectDto projectDto) {
        Project project = projectConverter.convertToEntity(projectDto);

        return projectRepository.create(project);
    }

    @Override
    public boolean update(ProjectDto projectDto) {
        Project project = projectConverter.convertToEntity(projectDto);

        return projectRepository.update(project);
    }

    @Override
    public ProjectDto findById(Long id) {
        Project project = projectRepository.findById(id);

        return projectConverter.convertToDto(project);
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream()
                .map(projectConverter::convertToDto)
                .toList();
    }

    @Override
    public boolean deleteById(Long id) {
        return projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectDto> searchByFilter(ProjectFilter filter) {
        return projectRepository.searchByFilter(filter).stream()
                .map(projectConverter::convertToDto)
                .toList();
    }
}

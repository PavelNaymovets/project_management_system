package com.digdes.pms.service.project.service;

import com.digdes.pms.api.dto.project.ProjectDto;
import com.digdes.pms.model.project.Project;
import com.digdes.pms.repository.project.ProjectDao;
import com.digdes.pms.repository.project.ProjectRepository;
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
        return false;
    }

    @Override
    public ProjectDto findById(Long id) {
        return null;
    }

    @Override
    public List<ProjectDto> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}

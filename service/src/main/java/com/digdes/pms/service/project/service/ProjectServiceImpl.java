package com.digdes.pms.service.project.service;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.dto.project.ProjectFilterDto;
import com.digdes.pms.exception.FieldIncorrectException;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.model.project.Project;
import com.digdes.pms.model.project.ProjectStatus;
import com.digdes.pms.repository.project.ProjectRepository;
import com.digdes.pms.repository.project.specification.ProjectSpecification;
import com.digdes.pms.service.project.converter.ProjectConverter;
import com.digdes.pms.service.project.validator.ProjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

import static com.digdes.pms.model.project.ProjectStatus.DRAFT;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectValidator projectValidator;
    private final ProjectConverter projectConverter;
    private final ProjectRepository projectRepository;
    private final MessageSource messageSource;

    @Override
    public ProjectDto create(ProjectDto projectDto) {
        projectValidator.validate(projectDto);
        Project project = projectConverter.convertToEntity(projectDto);
        project.setStatus(DRAFT.getStatus());
        Project createdProject = projectRepository.save(project);

        return projectConverter.convertToDto(createdProject);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto) {
        Project project = projectRepository.findById(projectDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("project.not.found.id", null, Locale.ENGLISH) + projectDto.getId()));
        checkUpdatableFields(projectDto, project);
        Project updatedProject = projectRepository.save(project);

        return projectConverter.convertToDto(updatedProject);
    }

    @Override
    public ProjectDto findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("project.not.found.id", null, Locale.ENGLISH) + id));

        return projectConverter.convertToDto(project);
    }

    @Transactional
    @Override
    public void updateStatus(Long id, String status) {
        checkStatus(status);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("project.not.found.id", null, Locale.ENGLISH) + id));
        project.setStatus(status);
    }

    @Override
    public List<ProjectDto> findAllByFilter(ProjectFilterDto filter) {
        Specification<Project> spec = Specification.where(null);

        if(!ObjectUtils.isEmpty(filter.getName())) {
            spec = spec.and(ProjectSpecification.nameLike(filter.getName()));
        }

        if(!ObjectUtils.isEmpty(filter.getStatus())) {
            checkStatus(filter.getStatus());
            spec = spec.and(ProjectSpecification.statusLike(filter.getStatus()));
        }

        if(!ObjectUtils.isEmpty(filter.getCode())) {
            spec = spec.and(ProjectSpecification.codeEqual(filter.getCode()));
        }

        if(!ObjectUtils.isEmpty(filter.getDescription())) {
            spec = spec.and(ProjectSpecification.descriptionLike(filter.getDescription()));
        }

        return projectRepository.findAll(spec).stream()
                .map(projectConverter::convertToDto)
                .toList();
    }

    @Override
    public ProjectDto deleteById(Long id) {
        Project deletedProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("project.not.found.id", null, Locale.ENGLISH) + id));
        projectRepository.delete(deletedProject);

        return projectConverter.convertToDto(deletedProject);
    }

    private void checkUpdatableFields(ProjectDto projectDto, Project project) {
        if (!ObjectUtils.isEmpty(projectDto.getStatus())) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("project.field.status.not.updatable", null, Locale.ENGLISH));
        }
        if (!ObjectUtils.isEmpty(projectDto.getName()) && !projectDto.getName().isBlank()) {
            project.setName(projectDto.getName());
        }
        if (!ObjectUtils.isEmpty(projectDto.getDescription()) && !projectDto.getDescription().isBlank()) {
            project.setDescription(projectDto.getDescription());
        }
        if (!ObjectUtils.isEmpty(projectDto.getCode())) {
            project.setCode(projectDto.getCode());
        }
    }

    private void checkStatus(String status) {
        if (ProjectStatus.check(status) == null) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("project.field.status.incorrect", null, Locale.ENGLISH));
        }
    }
}

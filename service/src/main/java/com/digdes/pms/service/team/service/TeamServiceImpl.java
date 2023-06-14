package com.digdes.pms.service.team.service;

import com.digdes.pms.dto.team.TeamDto;
import com.digdes.pms.dto.team.TeamFilterDto;
import com.digdes.pms.exception.NotSpecifiedIdException;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.exception.FieldIncorrectException;
import com.digdes.pms.exception.ValidationException;
import com.digdes.pms.model.project.Project;
import com.digdes.pms.model.team.Team;
import com.digdes.pms.repository.project.ProjectRepository;
import com.digdes.pms.repository.team.TeamRepository;
import com.digdes.pms.repository.team.specification.TeamSpecification;
import com.digdes.pms.service.project.converter.ProjectConverter;
import com.digdes.pms.service.team.converter.TeamConverter;
import com.digdes.pms.service.team.validator.TeamValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private static final Logger serviceLog = LoggerFactory.getLogger("service-log");
    private final TeamRepository teamRepository;
    private final TeamValidator teamValidator;
    private final TeamConverter teamConverter;
    private final ProjectRepository projectRepository;
    private final MessageSource messageSource;

    @Override
    public TeamDto create(TeamDto teamDto) {
        teamValidator.validate(teamDto);
        Team team = teamConverter.convertToEntity(teamDto);
        Team createdTeam = teamRepository.save(team);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("team.created", null, Locale.ENGLISH), createdTeam.getId()));

        return teamConverter.convertToDto(createdTeam);
    }

    @Override
    public TeamDto update(TeamDto teamDto) {
        if (ObjectUtils.isEmpty(teamDto.getId())) {
            throw new NotSpecifiedIdException(
                    messageSource.getMessage("team.field.id.null", null, Locale.ENGLISH));
        }

        Team team = teamRepository.findById(teamDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("team.not.found.id", null, Locale.ENGLISH) + teamDto.getId()));
        checkUpdatableFields(teamDto, team);
        Team updatedTeam = teamRepository.save(team);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("team.updated", null, Locale.ENGLISH), updatedTeam.getId()));

        return teamConverter.convertToDto(updatedTeam);
    }

    @Override
    public TeamDto deleteById(Long id) {
        Team deletedTeam = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("team.not.found.id", null, Locale.ENGLISH) + id));
        teamRepository.delete(deletedTeam);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("team.deleted", null, Locale.ENGLISH), id));

        return teamConverter.convertToDto(deletedTeam);
    }

    @Override
    public TeamDto findById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("team.not.found.id", null, Locale.ENGLISH) + id));
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("team.find", null, Locale.ENGLISH), id));

        return teamConverter.convertToDto(team);
    }

    @Override
    public List<TeamDto> findAllByFilter(TeamFilterDto filter) {
        Specification<Team> spec = Specification.where(null);

        if(!ObjectUtils.isEmpty(filter.getProject())) {
            Long id = filter.getProject().getId();
            spec = spec.and(TeamSpecification.projectIdEqual(id));
        }

        serviceLog.debug(messageSource.getMessage("team.find.by.filter", null, Locale.ENGLISH));

        return teamRepository.findAll(spec).stream()
                .map(teamConverter::convertToDto)
                .toList();
    }

    private void checkUpdatableFields(TeamDto teamDto, Team team) {
        if (!ObjectUtils.isEmpty(teamDto.getProject()) && teamDto.getProject().getId() != null) {
            Long projectId = teamDto.getProject().getId();
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            messageSource.getMessage("project.not.found.id", null, Locale.ENGLISH) + projectId));

            if (!ObjectUtils.isEmpty(teamDto.getProject().getCode()) ||
                !ObjectUtils.isEmpty(teamDto.getProject().getName()) ||
                !ObjectUtils.isEmpty(teamDto.getProject().getDescription()) ||
                !ObjectUtils.isEmpty(teamDto.getProject().getStatus())) {
                throw new FieldIncorrectException(
                        messageSource.getMessage("project.not.updatable.here", null, Locale.ENGLISH));
            }

            team.setProject(project);
        }
    }
}

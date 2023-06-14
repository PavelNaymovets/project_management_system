package com.digdes.pms.service.team.service;

import com.digdes.pms.dto.team.TeamMemberDto;
import com.digdes.pms.dto.team.TeamMemberFilterDto;
import com.digdes.pms.exception.FieldIncorrectException;
import com.digdes.pms.exception.NotSpecifiedIdException;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.team.Team;
import com.digdes.pms.model.team.TeamMember;
import com.digdes.pms.model.team.TeamMemberRole;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.repository.team.TeamMemberRepository;
import com.digdes.pms.repository.team.TeamRepository;
import com.digdes.pms.repository.team.specification.TeamMemberSpecification;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.project.converter.ProjectConverter;
import com.digdes.pms.service.team.converter.TeamConverter;
import com.digdes.pms.service.team.converter.TeamMemberConverter;
import com.digdes.pms.service.team.validator.TeamMemberValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {
    private static final Logger serviceLog = LoggerFactory.getLogger("service-log");
    private final TeamMemberValidator teamMemberValidator;
    private final TeamMemberConverter teamMemberConverter;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamConverter teamConverter;
    private final TeamRepository teamRepository;
    private final EmployeeConverter employeeConverter;
    private final EmployeeRepository employeeRepository;
    private final ProjectConverter projectConverter;
    private final MessageSource messageSource;

    @Override
    public TeamMemberDto create(TeamMemberDto teamMemberDto) {
        teamMemberValidator.validate(teamMemberDto);
        checkRole(teamMemberDto.getRole());
        Team team = teamRepository.findById(teamMemberDto.getTeam().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("team.not.found.id", null, Locale.ENGLISH) + teamMemberDto.getTeam().getId()));
        teamMemberDto.setTeam(teamConverter.convertToDto(team));
        Employee employee = employeeRepository.findById(teamMemberDto.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.id", null, Locale.ENGLISH) + teamMemberDto.getEmployee().getId()));
        teamMemberDto.setEmployee(employeeConverter.convertToDto(employee));
        TeamMember teamMember = teamMemberConverter.convertToEntity(teamMemberDto);
        TeamMember createdTeamMember = teamMemberRepository.save(teamMember);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("teamMember.created", null, Locale.ENGLISH), team.getId(), employee.getLogin()));

        return teamMemberConverter.convertToDto(createdTeamMember);
    }

    @Override
    public TeamMemberDto update(TeamMemberDto teamMemberDto) {
        if (ObjectUtils.isEmpty(teamMemberDto.getId())) {
            throw new NotSpecifiedIdException(
                    messageSource.getMessage("teamMember.field.id.null", null, Locale.ENGLISH));
        }

        TeamMember teamMember = teamMemberRepository.findById(teamMemberDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("teamMember.not.found.id", null, Locale.ENGLISH) + teamMemberDto.getId()));
        checkUpdatableFields(teamMemberDto, teamMember);
        TeamMember updatedTeamMember = teamMemberRepository.save(teamMember);
        Long teamId = updatedTeamMember.getTeam().getId();
        String employeeLogin = updatedTeamMember.getEmployee().getLogin();
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("teamMember.updated", null, Locale.ENGLISH), teamId, employeeLogin));

        return teamMemberConverter.convertToDto(updatedTeamMember);
    }

    @Override
    public TeamMemberDto deleteById(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("teamMember.not.found.id", null, Locale.ENGLISH) + id));
        teamMemberRepository.delete(teamMember);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("teamMember.deleted", null, Locale.ENGLISH), id, teamMember.getTeam().getId()));

        return teamMemberConverter.convertToDto(teamMember);
    }

    @Override
    public TeamMemberDto findById(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("teamMember.not.found.id", null, Locale.ENGLISH) + id));
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("teamMember.find", null, Locale.ENGLISH), id, teamMember.getTeam().getId()));

        return teamMemberConverter.convertToDto(teamMember);
    }

    @Override
    public List<TeamMemberDto> findAllByFilter(TeamMemberFilterDto filter) {
        Specification<TeamMember> spec = Specification.where(null);

        if(!ObjectUtils.isEmpty(filter.getTeam())) {
            Long id = filter.getTeam().getId();
            spec = spec.and(TeamMemberSpecification.teamIdEqual(id));
        }

        if(!ObjectUtils.isEmpty(filter.getEmployee())) {
            Long id = filter.getEmployee().getId();
            spec = spec.and(TeamMemberSpecification.employeeIdEqual(id));
        }

        if(!ObjectUtils.isEmpty(filter.getRole())) {
            checkRole(filter.getRole());
            spec = spec.and(TeamMemberSpecification.roleLike(filter.getRole()));
        }

        serviceLog.debug(messageSource.getMessage("teamMember.find.by.filter", null, Locale.ENGLISH));

        return teamMemberRepository.findAll(spec).stream()
                .map(teamMemberConverter::convertToDto)
                .toList();
    }

    @Transactional
    @Override
    public void updateStatus(Long id, String role) {
        checkRole(role);
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("teamMember.not.found.id", null, Locale.ENGLISH) + id));
        teamMember.setRole(role);
        serviceLog.debug(
                String.format(
                        messageSource.getMessage("teamMember.update.role", null, Locale.ENGLISH), id, role));
    }

    private void checkRole(String role) {
        if (TeamMemberRole.check(role) == null) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("teamMember.field.role.incorrect", null, Locale.ENGLISH));
        }
    }

    private void checkUpdatableFields(TeamMemberDto teamMemberDto, TeamMember teamMember) {
        if (!ObjectUtils.isEmpty(teamMemberDto.getTeam()) && !ObjectUtils.isEmpty(teamMemberDto.getTeam().getId())) {
            Long id = teamMemberDto.getTeam().getId();
            Team team = teamRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            messageSource.getMessage("team.not.found.id", null, Locale.ENGLISH) + id));

            if (!ObjectUtils.isEmpty(teamMemberDto.getTeam().getProject())) {
                throw new FieldIncorrectException(
                        messageSource.getMessage("team.not.updatable.here", null, Locale.ENGLISH));
            }

            teamMember.setTeam(team);
        }

        if (!ObjectUtils.isEmpty(teamMemberDto.getEmployee()) && !ObjectUtils.isEmpty(teamMemberDto.getEmployee().getId())) {
            Long id = teamMemberDto.getEmployee().getId();
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            messageSource.getMessage("employee.not.found.id", null, Locale.ENGLISH) + id));

            if (!ObjectUtils.isEmpty(teamMemberDto.getEmployee().getLastName()) ||
                !ObjectUtils.isEmpty(teamMemberDto.getEmployee().getFirstName()) ||
                !ObjectUtils.isEmpty(teamMemberDto.getEmployee().getMiddleName()) ||
                !ObjectUtils.isEmpty(teamMemberDto.getEmployee().getPersonalNumber()) ||
                !ObjectUtils.isEmpty(teamMemberDto.getEmployee().getPosition()) ||
                !ObjectUtils.isEmpty(teamMemberDto.getEmployee().getEmail()) ||
                !ObjectUtils.isEmpty(teamMemberDto.getEmployee().getLogin()) ||
                !ObjectUtils.isEmpty(teamMemberDto.getEmployee().getPassword()) ||
                !ObjectUtils.isEmpty(teamMemberDto.getEmployee().getStatus())) {
                throw new FieldIncorrectException(
                        messageSource.getMessage("employee.not.updatable.here", null, Locale.ENGLISH));
            }

            teamMember.setEmployee(employee);
        }

        if (!ObjectUtils.isEmpty(teamMemberDto.getRole())) {
            checkRole(teamMemberDto.getRole());
            teamMember.setRole(teamMemberDto.getRole());
        }
    }
}

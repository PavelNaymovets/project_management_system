package com.digdes.pms.service.team.service;

import com.digdes.pms.dto.team.TeamMemberDto;
import com.digdes.pms.dto.team.TeamMemberFilterDto;
import com.digdes.pms.exception.ResourceNotFoundException;
import com.digdes.pms.exception.FieldIncorrectException;
import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.team.Team;
import com.digdes.pms.model.team.TeamMember;
import com.digdes.pms.model.team.TeamMemberRole;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.repository.project.ProjectRepository;
import com.digdes.pms.repository.team.TeamMemberRepository;
import com.digdes.pms.repository.team.TeamRepository;
import com.digdes.pms.repository.team.specification.TeamMemberSpecification;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.project.converter.ProjectConverter;
import com.digdes.pms.service.team.converter.TeamConverter;
import com.digdes.pms.service.team.converter.TeamMemberConverter;
import com.digdes.pms.service.team.validator.TeamMemberValidator;
import lombok.RequiredArgsConstructor;
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
        teamMemberDto.getTeam().setProject(projectConverter.convertToDto(team.getProject()));
        Employee employee = employeeRepository.findById(teamMemberDto.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.id", null, Locale.ENGLISH) + teamMemberDto.getEmployee().getId()));
        teamMemberDto.setEmployee(employeeConverter.convertToDto(employee));
        TeamMember teamMember = teamMemberConverter.convertToEntity(teamMemberDto);
        TeamMember createdTeamMember = teamMemberRepository.save(teamMember);

        return teamMemberConverter.convertToDto(createdTeamMember);
    }

    @Override
    public TeamMemberDto update(TeamMemberDto teamMemberDto) {
        TeamMember teamMember = teamMemberRepository.findById(teamMemberDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("teamMember.not.found.id", null, Locale.ENGLISH) + teamMemberDto.getId()));
        Team team = teamRepository.findById(teamMemberDto.getTeam().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("team.not.found.id", null, Locale.ENGLISH) + teamMemberDto.getTeam().getId()));
        teamMemberDto.getTeam().setProject(projectConverter.convertToDto(team.getProject()));
        Employee employee = employeeRepository.findById(teamMemberDto.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("employee.not.found.id", null, Locale.ENGLISH) + teamMemberDto.getEmployee().getId()));
        teamMemberDto.setEmployee(employeeConverter.convertToDto(employee));
        checkUpdatableFields(teamMemberDto, teamMember);
        TeamMember updatedTeamMember = teamMemberRepository.save(teamMember);

        return teamMemberConverter.convertToDto(updatedTeamMember);
    }

    @Override
    public TeamMemberDto deleteById(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("teamMember.not.found.id", null, Locale.ENGLISH) + id));
        teamMemberRepository.delete(teamMember);

        return teamMemberConverter.convertToDto(teamMember);
    }

    @Override
    public TeamMemberDto findById(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("teamMember.not.found.id", null, Locale.ENGLISH) + id));

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
    }

    private void checkRole(String role) {
        if (TeamMemberRole.check(role) == null) {
            throw new FieldIncorrectException(
                    messageSource.getMessage("teamMember.field.role.incorrect", null, Locale.ENGLISH));
        }
    }

    private void checkUpdatableFields(TeamMemberDto teamMemberDto, TeamMember teamMember) {
        if (!ObjectUtils.isEmpty(teamMemberDto.getTeam()) && teamMemberDto.getTeam().getId() != null) {
            teamMember.setTeam(teamConverter.convertToEntity(teamMemberDto.getTeam()));
        }

        if (!ObjectUtils.isEmpty(teamMemberDto.getEmployee()) && teamMemberDto.getEmployee().getId() != null) {
            teamMember.setEmployee(employeeConverter.convertToEntity(teamMemberDto.getEmployee()));
        }

        if (!ObjectUtils.isEmpty(teamMemberDto.getRole())) {
            checkRole(teamMemberDto.getRole());
            teamMember.setRole(teamMemberDto.getRole());
        }
    }
}

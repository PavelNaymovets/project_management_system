package com.digdes.pms.service.team.converter;

import com.digdes.pms.dto.team.TeamMemberDto;
import com.digdes.pms.model.team.TeamMember;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@RequiredArgsConstructor
public class TeamMemberConverterImpl implements TeamMemberConverter {
    private final TeamConverter teamConverter;
    private final EmployeeConverter employeeConverter;

    @Override
    public TeamMember convertToEntity(TeamMemberDto teamMemberDto) {
        return TeamMember.builder()
                .id(teamMemberDto.getId())
                .team(ObjectUtils.isEmpty(teamMemberDto.getTeam()) ? null : teamConverter.convertToEntity(teamMemberDto.getTeam()))
                .employee(ObjectUtils.isEmpty(teamMemberDto.getEmployee()) ? null : employeeConverter.convertToEntity(teamMemberDto.getEmployee()))
                .role(teamMemberDto.getRole())
                .build();
    }

    @Override
    public TeamMemberDto convertToDto(TeamMember teamMember) {
        return TeamMemberDto.builder()
                .id(teamMember.getId())
                .team(ObjectUtils.isEmpty(teamMember.getTeam()) ? null : teamConverter.convertToDto(teamMember.getTeam()))
                .employee(ObjectUtils.isEmpty(teamMember.getEmployee()) ? null : employeeConverter.convertToDto(teamMember.getEmployee()))
                .role(teamMember.getRole())
                .build();
    }
}

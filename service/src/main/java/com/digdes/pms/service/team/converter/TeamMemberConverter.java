package com.digdes.pms.service.team.converter;

import com.digdes.pms.dto.team.TeamMemberDto;
import com.digdes.pms.model.team.TeamMember;
import com.digdes.pms.service.util.converter.Converter;

public interface TeamMemberConverter extends Converter<TeamMember, TeamMemberDto> {
    @Override
    TeamMember convertToEntity(TeamMemberDto teamMemberDto);

    @Override
    TeamMemberDto convertToDto(TeamMember teamMember);
}

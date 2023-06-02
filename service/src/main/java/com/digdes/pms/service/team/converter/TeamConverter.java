package com.digdes.pms.service.team.converter;

import com.digdes.pms.dto.team.TeamDto;
import com.digdes.pms.model.team.Team;
import com.digdes.pms.service.util.converter.Converter;

public interface TeamConverter extends Converter<Team, TeamDto> {
    @Override
    Team convertToEntity(TeamDto teamDto);

    @Override
    TeamDto convertToDto(Team team);
}

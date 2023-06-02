package com.digdes.pms.service.team.converter;

import com.digdes.pms.dto.team.TeamDto;
import com.digdes.pms.model.team.Team;
import com.digdes.pms.service.project.converter.ProjectConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@RequiredArgsConstructor
public class TeamConverterImpl implements TeamConverter {
    private final ProjectConverter projectConverter;

    @Override
    public Team convertToEntity(TeamDto teamDto) {
        return Team.builder()
                .id(teamDto.getId())
                .project(ObjectUtils.isEmpty(teamDto.getProject()) ? null : projectConverter.convertToEntity(teamDto.getProject()))
                .build();
    }

    @Override
    public TeamDto convertToDto(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .project(ObjectUtils.isEmpty(team.getProject()) ? null : projectConverter.convertToDto(team.getProject()))
                .build();
    }
}

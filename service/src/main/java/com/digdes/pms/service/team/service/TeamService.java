package com.digdes.pms.service.team.service;

import com.digdes.pms.dto.team.TeamDto;
import com.digdes.pms.dto.team.TeamFilterDto;
import com.digdes.pms.service.util.service.ServiceCRUD;

import java.util.List;

public interface TeamService extends ServiceCRUD<TeamDto, TeamFilterDto> {
    @Override
    TeamDto create(TeamDto teamDto);

    @Override
    TeamDto update(TeamDto teamDto);

    @Override
    TeamDto deleteById(Long id);

    @Override
    TeamDto findById(Long id);

    @Override
    List<TeamDto> findAllByFilter(TeamFilterDto filter);
}

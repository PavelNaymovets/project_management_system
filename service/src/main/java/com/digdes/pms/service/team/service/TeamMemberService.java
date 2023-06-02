package com.digdes.pms.service.team.service;

import com.digdes.pms.dto.team.TeamMemberDto;
import com.digdes.pms.dto.team.TeamMemberFilterDto;
import com.digdes.pms.service.util.service.ServiceCRUD;
import com.digdes.pms.service.util.service.ServiceUpdateStatus;

import java.util.List;

public interface TeamMemberService extends ServiceCRUD<TeamMemberDto,TeamMemberFilterDto>, ServiceUpdateStatus {
    @Override
    TeamMemberDto create(TeamMemberDto teamMemberDto);

    @Override
    TeamMemberDto update(TeamMemberDto teamMemberDto);

    @Override
    TeamMemberDto deleteById(Long id);

    @Override
    TeamMemberDto findById(Long id);

    @Override
    List<TeamMemberDto> findAllByFilter(TeamMemberFilterDto filter);

    @Override
    void updateStatus(Long id, String status);
}

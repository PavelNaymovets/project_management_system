package com.digdes.pms.api.dto.team;

import com.digdes.pms.api.dto.project.ProjectDto;
import lombok.Data;

import java.util.List;

@Data
public class TeamDto {
    private Long id;
    private ProjectDto project;
    private List<TeamMemberDto> members;
}

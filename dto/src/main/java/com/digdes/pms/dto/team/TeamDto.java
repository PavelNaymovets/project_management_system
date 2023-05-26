package com.digdes.pms.dto.team;

import com.digdes.pms.dto.project.ProjectDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Модель команды")
public class TeamDto {
    @Schema(description = "id команды",
            required = true,
            example = "1",
            type = "Long")
    private Long id;

    @Schema(description = "Проект команды",
            required = true,
            type = "ProjectDto")
    private ProjectDto project;

    @Schema(description = "Участники команды",
            type = "List<TeamMemberDto>")
    private List<TeamMemberDto> members;
}

package com.digdes.pms.dto.team;

import com.digdes.pms.dto.project.ProjectDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель фильтра поиска команды")
public class TeamFilterDto {
    @Schema(description = "Проект команды",
            required = true,
            type = "ProjectDto")
    private ProjectDto project;
}

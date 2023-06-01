package com.digdes.pms.dto.team;

import com.digdes.pms.dto.employee.EmployeeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Модель участника команды")
public class TeamMemberDto {
    @Schema(description = "id участника команды",
            required = true,
            example = "1",
            type = "Long")
    private Long id;

    @Schema(description = "Команда",
            required = true,
            type = "TeamDto")
    private TeamDto team;

    @Schema(description = "Участник команды",
            required = true,
            type = "EmployeeDto")
    private EmployeeDto employee;

    @Schema(description = "Роль в команде",
            required = true,
            example = "руководитель проекта",
            type = "String")
    private String role;
}

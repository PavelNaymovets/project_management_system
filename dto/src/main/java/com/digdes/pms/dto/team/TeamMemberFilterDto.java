package com.digdes.pms.dto.team;

import com.digdes.pms.dto.employee.EmployeeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель фильтра поиска участника команды")
public class TeamMemberFilterDto {
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

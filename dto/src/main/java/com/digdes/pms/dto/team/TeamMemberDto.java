package com.digdes.pms.dto.team;

import com.digdes.pms.dto.employee.EmployeeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель участника команды")
public class TeamMemberDto {
    @Schema(description = "id участника команды",
            required = true,
            example = "1",
            type = "Long")
    private Long id;

    @Schema(description = "Участник команды",
            required = true,
            type = "EmployeeDto")
    private EmployeeDto employee;

    @Schema(description = "Роль в команде",
            required = true,
            example = "Руководитель проекта, Аналитик, Разработчик, Тестировщик",
            type = "String")
    private String role;

    @Schema(description = "Команда",
            required = true,
            type = "TeamDto")
    private TeamDto team;
}

package com.digdes.pms.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Модель работника")
public class EmployeeDto {
    @Schema(description = "id работника",
            required = true,
            example = "1",
            type = "Long")
    private Long id;

    @Schema(description = "Уникальный идентификатор на уровне всей программы работника",
            required = true,
            example = "384f508c-3016-3bf1-9334-6ccc1ca21c36",
            type = "String")
    private String personalNumber;

    @Schema(description = "Фамилия работника",
            required = true,
            example = "Прохорова",
            type = "String")
    private String lastName;

    @Schema(description = "Имя работника",
            required = true,
            example = "Инна",
            type = "String")
    private String firstName;

    @Schema(description = "Отчество работника",
            example = "Андреевна",
            type = "String")
    private String middleName;

    @Schema(description = "Должность",
            example = "Инженер ui тестирования",
            type = "String")
    private String position;

    @Schema(description = "Почта",
            example = "vil534@example.com",
            type = "String")
    private String email;
}

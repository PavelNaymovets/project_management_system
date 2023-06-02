package com.digdes.pms.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель фильтра поиска работника")
public class EmployeeFilterDto {
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

    @Schema(description = "Учетная запись",
            example = "vil53",
            type = "String")
    private String login;

    @Schema(description = "Почта",
            example = "vil534@example.com",
            type = "String")
    private String email;

    @Schema(description = "Статус",
            example = "активный",
            type = "String")
    private String status;
}

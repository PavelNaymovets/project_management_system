package com.digdes.pms.dto.task;

import com.digdes.pms.dto.employee.EmployeeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Schema(description = "Модель фильтра поиска задач")
public class TaskFilterDto {
    @Schema(description = "Наименование задачи",
            example = "Подготовить Liquebase-скрипт",
            type = "String")
    String name;

    @Schema(description = "Статус задачи",
            example = "новая",
            type = "String")
    String status;

    @Schema(description = "Исполнитель задачи",
            type = "EmployeeDto")
    EmployeeDto employee;

    @Schema(description = "Автор задачи",
            type = "EmployeeDto")
    EmployeeDto author;

    @Schema(description = "Крайний минимальный срок выполнения задачи",
            example = "2023-02-09",
            type = "LocalDate")
    LocalDate deadlineMin;

    @Schema(description = "Крайний максимальный срок выполнения задачи",
            example = "2029-02-09",
            type = "LocalDate")
    LocalDate deadlineMax;

    @Schema(description = "Минимальная дата создания задачи",
            example = "2023-05-23 00:00:00",
            type = "LocalDate")
    LocalDateTime createdAtMin;

    @Schema(description = "Максимальная дата создания задачи",
            example = "2024-05-23 00:00:00",
            type = "LocalDate")
    LocalDateTime createdAtMax;
}

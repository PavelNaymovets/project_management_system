package com.digdes.pms.dto.task;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.project.ProjectDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Schema(description = "Модель задачи")
public class TaskDto {
    @Schema(description = "id задачи",
            required = true,
            example = "1",
            type = "Long")
    private Long id;

    @Schema(description = "Наименование задачи",
            required = true,
            example = "Подготовить Liquebase-скрипт",
            type = "String")
    private String name;

    @Schema(description = "Описание задачи",
            example = "Переложить скрипт, созданный в п.2 на Liquebase-скрипт(xml/yaml что хотите). В этом ДЗ запускать не требуется",
            type = "String")
    private String description;

    @Schema(description = "Проект в котором задача поставлена",
            required = true,
            type = "ProjectDto")
    private ProjectDto project;

    @Schema(description = "Исполнитель задачи",
            type = "EmployeeDto")
    private EmployeeDto employee;

    @Schema(description = "Трудозатраты в часах",
            required = true,
            example = "10",
            type = "Long")
    private Long laborCosts;

    @Schema(description = "Крайний срок выполнения задачи",
            required = true,
            example = "2029-02-09",
            type = "LocalDate")
    private LocalDate deadline;

    @Schema(description = "Статус задачи",
            required = true,
            example = "Новая, В работе, Выполнена, Закрыта",
            type = "String")
    private String status;

    @Schema(description = "Автор задачи",
            required = true,
            type = "EmployeeDto")
    private EmployeeDto author;
}

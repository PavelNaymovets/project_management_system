package com.digdes.pms.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Модель проекта")
public class ProjectDto {
    @Schema(description = "id проекта",
            required = true,
            example = "1",
            type = "Long")
    private Long id;

    @Schema(description = "Код проекта",
            required = true,
            example = "1220680",
            type = "Long")
    private Long code;

    @Schema(description = "Название проекта",
            required = true,
            example = "система управления проектами",
            type = "String")
    private String name;

    @Schema(description = "Описание проекта",
            example = "Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт",
            type = "String")
    private String description;

    @Schema(description = "Статус проекта ()",
            required = true,
            example = "Черновик, В разработке, В тестировании, Завершен",
            type = "Long")
    private String status;
}

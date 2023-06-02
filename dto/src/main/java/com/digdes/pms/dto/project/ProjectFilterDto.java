package com.digdes.pms.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель фильтра поиска проекта")
public class ProjectFilterDto {
    @Schema(description = "Название проекта",
            required = true,
            example = "система управления проектами",
            type = "String")
    private String name;

    @Schema(description = "Код проекта",
            required = true,
            example = "1220680",
            type = "Long")
    private Long code;

    @Schema(description = "Статус проекта ()",
            required = true,
            example = "черновик",
            type = "Long")
    private String status;

    @Schema(description = "Описание проекта",
            example = "Взаимодействие корпорации и клиента традиционно специфицирует ролевой анализ зарубежного опыта Баинг и селлинг, безусловно, экономит продвигаемый повторный контакт",
            type = "String")
    private String description;
}

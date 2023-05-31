package com.digdes.pms.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
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
}

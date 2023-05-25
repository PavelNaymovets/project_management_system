package com.digdes.pms.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Модель исключения в приложении")
public class AppError {
    @Schema(description = "Статус код",
            required = true,
            example = "401",
            type = "int")
    private int statusCode;

    @Schema(description = "Сообщение об ошибке",
            required = true,
            example = "Не заполнено обязательное поле - Наименование задачи",
            type = "int")
    private String message;
}

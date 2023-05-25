package com.digdes.pms.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель данных для входа в систему")
public class JwtRequestDto {
    @Schema(description = "Логин работника",
            required = true,
            example = "vil53",
            type = "String")
    private String login;

    @Schema(description = "Пароль работника",
            required = true,
            example = "100",
            type = "String")
    private String password;
}

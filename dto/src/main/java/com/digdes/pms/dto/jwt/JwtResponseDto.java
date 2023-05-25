package com.digdes.pms.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель jwt токена")
public class JwtResponseDto {
    @Schema(description = "Jwt токен",
            required = true,
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWw1MyIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJleHAiOjE2ODUwMjI2NjksImlhdCI6MTY4NTAxOTA2OX0.xPp2lPLG93sNk8POtXqtSYso2VOt9HD1YtdyI1n6bgM",
            type = "String")
    private String token;
}

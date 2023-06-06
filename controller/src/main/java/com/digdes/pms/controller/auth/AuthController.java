package com.digdes.pms.controller.auth;

import com.digdes.pms.auth.util.AuthEmployeeService;
import com.digdes.pms.auth.util.JwtTokenUtil;
import com.digdes.pms.dto.jwt.JwtRequestDto;
import com.digdes.pms.dto.jwt.JwtResponseDto;
import com.digdes.pms.exception.AppError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Контроллер аутентификации", description = "Содержит endpoint для получения токена")
public class AuthController {
    private static final Logger authenticationLog = LoggerFactory.getLogger("auth-log");
    private final AuthEmployeeService authEmployeeService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final MessageSource messageSource;

    @Operation(summary = "Получение jwt токена.",
            responses = {
                    @ApiResponse(description = "Токен успешно сгенерирован и возвращен",
                                 responseCode = "200",
                                 content = @Content(schema = @Schema(implementation = JwtResponseDto.class))
                    ),
                    @ApiResponse(description = "Неправильный логин или пароль. Возвращается объект AppError с кодом ошибки и текстом",
                                 responseCode = "401",
                                 content = @Content(schema = @Schema(implementation = AppError.class))
                    )

            }
    )
    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody
                                             @Parameter(description = "Объект JwtRequestDto - содержит параметрами аутентификации", required = true)
                                             JwtRequestDto authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            authenticationLog.debug(e.getMessage(), e);

            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(),
                            messageSource.getMessage(
                                    "authentication.login.password.incorrect", null, Locale.ENGLISH)),
                    HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = authEmployeeService.loadUserByUsername(authRequest.getLogin());
        String token = jwtTokenUtil.generateToken(userDetails);
        authenticationLog.debug(
                messageSource.getMessage(
                        "authentication.token.created", null, Locale.ENGLISH) + authRequest.getLogin());

        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}

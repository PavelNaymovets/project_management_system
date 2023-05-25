package com.digdes.pms.app.config.openApi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Система управления проектами")
                                .description("Программа для управления проектами. Содержит функционал:\n" +
                                        "* Управление сотрудниками компании (контроллер работника).\n" +
                                        "* Управление проектами и их жизненным циклом (контроллер проекта).\n" +
                                        "* Управление задачами внутри проекта и их жизненным циклом (контроллер задач).\n" +
                                        "* Управление командами внутри проекта (контроллер команды).\n" +
                                        "* Аутентификация в системе (контроллер аутентификации).\n")
                                .version("1")
                );
    }
}

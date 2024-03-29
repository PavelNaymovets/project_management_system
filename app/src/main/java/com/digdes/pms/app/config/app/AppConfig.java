package com.digdes.pms.app.config.app;

import com.digdes.pms.auth.config.SecurityConfig;
import com.digdes.pms.repository.config.RepositoryConfig;
import com.digdes.pms.service.config.email.EmailConfig;
import com.digdes.pms.service.config.queue.RabbitMQConfig;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = {"com.digdes.pms.auth", "com.digdes.pms.controller", "com.digdes.pms.service"})
@Import({RepositoryConfig.class, SecurityConfig.class, EmailConfig.class, RabbitMQConfig.class})
public class AppConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/message/exception/ex_authentication",
                "classpath:/message/exception/ex_has_deleted_status",
                "classpath:/message/exception/ex_resource_not_found",
                "classpath:/message/exception/ex_validation",
                "classpath:/message/log/log");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}

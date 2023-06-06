package com.digdes.pms.service.config;

import com.digdes.pms.service.task.email.collector.thymeleaf.EmailFieldNameProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("letter.properties")
public class EmailConfig {
    @Bean
    @ConfigurationProperties(prefix = "email")
    public EmailFieldNameProperties emailFieldProperties() {
        return new EmailFieldNameProperties();
    }
}

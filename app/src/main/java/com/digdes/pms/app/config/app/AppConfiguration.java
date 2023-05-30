package com.digdes.pms.app.config.app;

import com.digdes.pms.auth.config.SecurityConfig;
import com.digdes.pms.repository.config.RepositoryConfig;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = {"com.digdes.pms.auth", "com.digdes.pms.controller", "com.digdes.pms.service"})
@Import({RepositoryConfig.class, SecurityConfig.class})
public class AppConfiguration {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/message/exception/employee_has_deleted",
                "classpath:/message/exception/resource_not_found",
                "classpath:/message/exception/validation");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}

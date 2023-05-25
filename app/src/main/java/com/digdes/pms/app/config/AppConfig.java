package com.digdes.pms.app.config;

import com.digdes.pms.auth.config.SecurityConfig;
import com.digdes.pms.repository.config.RepositoryConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.digdes.pms.auth", "com.digdes.pms.controller", "com.digdes.pms.service"})
@Import({RepositoryConfig.class, SecurityConfig.class})
public class AppConfig {
}

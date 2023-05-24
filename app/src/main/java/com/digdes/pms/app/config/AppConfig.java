package com.digdes.pms.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.digdes.pms.controller", "com.digdes.pms.service"})
public class AppConfig {
}

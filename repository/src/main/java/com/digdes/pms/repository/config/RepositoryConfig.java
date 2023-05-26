package com.digdes.pms.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.digdes.pms.repository")
@EntityScan(basePackages = "com.digdes.pms.model")
public class RepositoryConfig {
}

package com.digdes.pms.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.digdes.pms.controller", "com.digdes.pms.service"})
@EnableJpaRepositories(basePackages = "com.digdes.pms.repository")
@EntityScan(basePackages = "com.digdes.pms.model")
public class ProjectManagementSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementSystemApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Context is up");
    }
}

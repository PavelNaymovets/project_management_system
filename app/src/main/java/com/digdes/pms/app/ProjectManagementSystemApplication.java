package com.digdes.pms.app;

import com.digdes.pms.app.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfiguration.class)
public class ProjectManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementSystemApplication.class);
    }
}

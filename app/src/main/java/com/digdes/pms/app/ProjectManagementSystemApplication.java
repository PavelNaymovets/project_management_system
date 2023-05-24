package com.digdes.pms.app;

import com.digdes.pms.app.config.AppConfig;
import com.digdes.pms.repository.config.RepositoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RepositoryConfig.class, AppConfig.class})
public class ProjectManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementSystemApplication.class);
    }
}

package com.digdes.pms.service;

import com.digdes.pms.api.dto.project.ProjectDto;
import com.digdes.pms.service.project.service.ProjectService;
import com.digdes.pms.service.project.service.ProjectServiceImpl;

public class DemoAppHomeWork4 {
    public static void main(String[] args) {
        ProjectService projectService = new ProjectServiceImpl();

        ProjectDto projectDto = ProjectDto.builder()
                .code(85947321L)
                .name("project_2")
                .description("interest project")
                .status("В работе")
                .build();

        //Создание проекта
        projectService.create(projectDto);
    }
}

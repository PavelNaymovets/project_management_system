package com.digdes.pms.service;

import com.digdes.pms.dto.project.ProjectDto;
import com.digdes.pms.repository.project.util.filter.ProjectFilter;
import com.digdes.pms.service.project.service.ProjectService;
import com.digdes.pms.service.project.service.ProjectServiceImpl;

public class DemoApp {
    public static void main(String[] args) {
        ProjectService projectService = new ProjectServiceImpl();

        ProjectDto projectDto = ProjectDto.builder()
                .code(85947321L)
                .name("тестовый проект")
                .description("тестовое описание")
                .build();

        //Создание проекта
        projectService.create(projectDto);

        //Обновление проекта
        projectDto.setId(10L);
        projectDto.setName("Новое");
        projectService.update(projectDto);

        //Поиск по id
        System.out.println(projectService.findById(1L));

        //Поиск всех записей
        System.out.println(projectService.findAll());

        //Удаление записи по id
        projectService.deleteById(10L);

        //Поиск по фильтру
        ProjectFilter projectFilter = new ProjectFilter();
        projectFilter.setLastName("Прохорова");
        projectFilter.setRole("аналитик");
        System.out.println(projectService.searchByFilter(projectFilter));
    }
}

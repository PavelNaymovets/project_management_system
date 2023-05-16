package com.digdes.pms.project.model;

import java.time.LocalDateTime;

public class Project {
    private Long code;
    private String name;
    private String description;
    private ProjectStatus status; //TODO отдельная таблица в БД со статусами: черновик, в разработке, в тестировании, завершен.
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

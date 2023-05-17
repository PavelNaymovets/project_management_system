package com.digdes.pms.team.model;

import java.time.LocalDateTime;

public class TeamMember {
    private Long id;
    private String employeeEmail;
    private ProjectRole role; //TODO отдельная таблица в БД с ролями: руководитель проекта, аналитик, разработчик, тестировщик.
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

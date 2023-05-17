package com.digdes.pms.task.model;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String name;
    private String description;
    private String employeeEmail;
    private Long LaborCosts;
    private LocalDateTime deadline;
    private TaskStatus status; //TODO отдельная таблица в БД со статусами: новая, в работе, выполнена, закрыта.
    private String authorEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

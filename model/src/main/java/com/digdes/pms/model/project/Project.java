package com.digdes.pms.model.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Project {
    private Long id;
    private Long code;
    private String name;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

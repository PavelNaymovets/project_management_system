package com.digdes.pms.api.dto.project;

import lombok.Data;

@Data
public class ProjectDto {
    private Long id;
    private Long code;
    private String name;
    private String description;
    private String status;
}

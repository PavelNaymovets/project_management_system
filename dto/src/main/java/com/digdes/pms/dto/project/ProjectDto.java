package com.digdes.pms.dto.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectDto {
    private Long id;
    private Long code;
    private String name;
    private String description;
    private String status;
}

package com.digdes.pms.model.team;

import com.digdes.pms.model.project.Project;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Team {
    private Long id;
    private Project project;
    private List<TeamMember> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

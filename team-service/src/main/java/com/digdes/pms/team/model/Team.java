package com.digdes.pms.team.model;

import java.time.LocalDateTime;
import java.util.List;

public class Team {
    private Long id;
    private Long projectCode;
    private List<TeamMember> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

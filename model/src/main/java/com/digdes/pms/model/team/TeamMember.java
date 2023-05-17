package com.digdes.pms.model.team;

import com.digdes.pms.model.employee.Employee;

import java.time.LocalDateTime;

public class TeamMember {
    private Long id;
    private Employee member;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

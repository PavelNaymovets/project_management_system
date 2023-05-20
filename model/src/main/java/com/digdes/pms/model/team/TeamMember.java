package com.digdes.pms.model.team;

import com.digdes.pms.model.employee.Employee;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamMember {
    private Long id;
    private Employee member;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

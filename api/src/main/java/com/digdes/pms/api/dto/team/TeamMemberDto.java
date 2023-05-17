package com.digdes.pms.api.dto.team;

import com.digdes.pms.api.dto.employee.EmployeeDto;
import lombok.Data;

@Data
public class TeamMemberDto {
    private Long id;
    private EmployeeDto member;
    private String role;
}

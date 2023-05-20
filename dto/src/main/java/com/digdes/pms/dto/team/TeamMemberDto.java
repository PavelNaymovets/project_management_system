package com.digdes.pms.dto.team;

import com.digdes.pms.dto.employee.EmployeeDto;
import lombok.Data;

@Data
public class TeamMemberDto {
    private Long id;
    private EmployeeDto employee;
    private String role;
    private TeamDto team;
}

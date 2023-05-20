package com.digdes.pms.api.dto.employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
    private Long id;
    private String name;
    private String lastName;
    private String middleName;
    private String position;
    private String email;
}

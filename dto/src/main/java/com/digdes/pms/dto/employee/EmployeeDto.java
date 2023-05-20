package com.digdes.pms.dto.employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
    private Long id;
    private String personalNumber;
    private String lastName;
    private String firstName;
    private String middleName;
    private String position;
    private String email;
}

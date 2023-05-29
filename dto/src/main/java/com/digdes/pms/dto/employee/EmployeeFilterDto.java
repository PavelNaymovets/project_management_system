package com.digdes.pms.dto.employee;

import lombok.Data;

@Data
public class EmployeeFilterDto {
    private String lastName;
    private String firstName;
    private String middleName;
    private String login;
    private String email;
    private boolean status = Boolean.TRUE;
}

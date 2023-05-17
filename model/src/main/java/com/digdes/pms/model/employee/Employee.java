package com.digdes.pms.model.employee;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Employee {
    private Long id;
    private String name;
    private String lastName;
    private String middleName;
    private String position;
    private String login;
    private String email;
    private boolean status; //1 - активный, 0 - удаленный.
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

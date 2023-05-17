package com.digdes.pms.employee.model;

import java.time.LocalDateTime;

public class Employee {
    private Long id;
    private String name;
    private String lastName;
    private String middleName;
    private String position;
    private String account; //TODO уточнить что означает учетная запись
    private String email;
    private boolean status; //1 - активный, 0 - удаленный.
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

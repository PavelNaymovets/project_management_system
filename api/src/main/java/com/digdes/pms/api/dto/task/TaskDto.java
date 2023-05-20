package com.digdes.pms.api.dto.task;

import com.digdes.pms.api.dto.employee.EmployeeDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private EmployeeDto employee;
    private Long laborCosts;
    private LocalDateTime deadline;
    private String status;
    private EmployeeDto author;
}

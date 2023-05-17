package com.digdes.pms.model.task;

import com.digdes.pms.model.employee.Employee;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {
    private Long id;
    private String name;
    private String description;
    private Employee employee;
    private Long laborCosts;
    private LocalDateTime deadline;
    private String status;
    private Employee author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

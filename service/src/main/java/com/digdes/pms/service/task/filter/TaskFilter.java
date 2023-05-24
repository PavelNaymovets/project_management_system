package com.digdes.pms.service.task.filter;

import com.digdes.pms.dto.employee.EmployeeDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskFilter {
    String name;
    String status;
    EmployeeDto employee;
    EmployeeDto author;
    LocalDate deadlineMin;
    LocalDate deadlineMax;
    LocalDateTime createdAtMin;
    LocalDateTime createdAtMax;
}

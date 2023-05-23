package com.digdes.pms.service.task.filter;

import com.digdes.pms.dto.employee.EmployeeDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskFilter {
    String name;
    String status;
    EmployeeDto employee;
    EmployeeDto author;
    LocalDateTime deadline;
}

package com.digdes.pms.dto.task;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.project.ProjectDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private ProjectDto project;
    private EmployeeDto employee;
    private Long laborCosts;
    private LocalDate deadline;
    private String status;
    private EmployeeDto author;
}

package com.digdes.pms.service.email;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.task.TaskDto;

public interface EmailService {
    boolean sendHtmlMessage(EmployeeDto employee, TaskDto task);
}

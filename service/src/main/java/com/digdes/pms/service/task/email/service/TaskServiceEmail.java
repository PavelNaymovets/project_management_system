package com.digdes.pms.service.task.email.service;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.service.util.service.ServiceEmail;

public interface TaskServiceEmail extends ServiceEmail<EmployeeDto, TaskDto> {
    @Override
    boolean sendHtmlMessage(EmployeeDto toSend, TaskDto entitySend);
}

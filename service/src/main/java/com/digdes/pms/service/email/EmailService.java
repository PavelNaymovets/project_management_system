package com.digdes.pms.service.email;

import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.task.Task;

public interface EmailService {
    void sendHtmlMessage(Employee employee, Task task);
}

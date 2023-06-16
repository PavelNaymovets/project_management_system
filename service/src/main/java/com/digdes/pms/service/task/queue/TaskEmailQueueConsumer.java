package com.digdes.pms.service.task.queue;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.exception.EmailSendException;
import com.digdes.pms.repository.employee.EmployeeRepository;
import com.digdes.pms.repository.task.TaskRepository;
import com.digdes.pms.service.employee.converter.EmployeeConverter;
import com.digdes.pms.service.task.converter.TaskConverter;
import com.digdes.pms.service.task.email.service.TaskServiceEmail;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class TaskEmailQueueConsumer {
    private static final Logger emailLog = LoggerFactory.getLogger("email-log");
    private final TaskServiceEmail taskServiceEmail;
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final MessageSource messageSource;

    @RabbitListener(queues = "${queue.name}")
    public void receiveMessage(String message) {
        String[] employeeTaskId = message.split("_", 2);
        Long employeeId = Long.parseLong(employeeTaskId[0]);
        Long taskId = Long.parseLong(employeeTaskId[1]);
        EmployeeDto employeeDto = employeeConverter.convertToDto(employeeRepository.findById(employeeId).get());
        TaskDto taskDto = taskConverter.convertToDto(taskRepository.findById(taskId).get());
        try {
            if (!taskServiceEmail.sendHtmlMessage(employeeDto, taskDto)) {
                throw new EmailSendException(
                        messageSource.getMessage("email.send.fail", null, Locale.ENGLISH));
            }
        } catch (EmailSendException e) {
            emailLog.debug(e.getMessage(), e);
        }
    }
}

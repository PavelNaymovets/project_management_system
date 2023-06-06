package com.digdes.pms.service.task.email.collector;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.service.util.collector.LetterCollector;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface TaskHtmlLetterCollector extends LetterCollector<EmployeeDto, TaskDto> {
    @Override
    void collect(MimeMessage message,
                 MimeMessageHelper helper,
                 Context context,
                 EmployeeDto toSend,
                 TaskDto entitySend) throws MessagingException;
}

package com.digdes.pms.service.task.email.collector.thymeleaf;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.service.task.email.collector.TaskHtmlLetterCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskThymeleafLetterCollector implements TaskHtmlLetterCollector {
    private final EmailFieldNameProperties emailFields;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void collect(MimeMessage message,
                        MimeMessageHelper helper,
                        Context context,
                        EmployeeDto employeeDto,
                        TaskDto taskDto) throws MessagingException {
        Map<String, Object> properties = new HashMap<>();
        properties.put(emailFields.getEmployeeName(), employeeDto.getFirstName());
        properties.put(emailFields.getProjectName(), taskDto.getProject().getName());
        properties.put(emailFields.getSubscriptionDate(), LocalDate.now().toString());
        properties.put(emailFields.getTask(), taskDto);

        context.setVariables(properties);

        String[] sendToEmails = new String[]{employeeDto.getEmail(), emailFields.getReplyToEmail()};
        helper.setFrom(emailFields.getFromEmail());
        helper.setTo(sendToEmails);
        helper.setSubject(emailFields.getSubject());
        String html = templateEngine.process(emailFields.getThymeleafTemplate(), context);
        helper.setText(html, true);
    }
}

package com.digdes.pms.service.task.email.collector.thymeleaf;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.service.task.email.collector.TaskHtmlLetterCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class TaskThymeleafLetterCollector implements TaskHtmlLetterCollector {
    @Value("${email.send.from}")
    private String fromEmail;
    @Value("${email.reply.to}")
    private String replyToEmail;
    @Value("${email.subject}")
    private String subject;
    @Value("${email.template}")
    private String thymeleafTemplate;
    @Value("${email.th.param.employee.name}")
    private String employeeName;
    @Value("${email.th.param.project.name}")
    private String projectName;
    @Value("${email.th.param.subscription.date}")
    private String subscriptionDate;
    @Value("${email.th.param.task}")
    private String task;

    private SpringTemplateEngine templateEngine;

    @Override
    public void collect(MimeMessage message,
                        MimeMessageHelper helper,
                        Context context,
                        EmployeeDto employeeDto,
                        TaskDto taskDto) throws MessagingException {
        Map<String, Object> properties = new HashMap<>();
        properties.put(employeeName, employeeDto.getFirstName());
        properties.put(projectName, taskDto.getProject().getName());
        properties.put(subscriptionDate, LocalDate.now().toString());
        properties.put(task, taskDto);

        context.setVariables(properties);

        String[] sendToEmails = new String[]{employeeDto.getEmail(), replyToEmail};
        helper.setFrom(fromEmail);
        helper.setTo(sendToEmails);
        helper.setSubject(subject);
        String html = templateEngine.process(thymeleafTemplate, context);
        helper.setText(html, true);
    }

    @Autowired
    public void setSpringTemplateEngine(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
}

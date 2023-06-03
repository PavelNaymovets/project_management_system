package com.digdes.pms.service.email;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.task.TaskDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Value("${email.send.from}")
    private String fromEmail;
    @Value("${email.reply.to}")
    private String replyToEmail;
    @Value("${email.subject}")
    private String subject;
    @Value("${email.template}")
    private String template;
    @Value("${email.th.param.employee.name}")
    private String employeeName;
    @Value("${email.th.param.project.name}")
    private String projectName;
    @Value("${email.th.param.subscription.date}")
    private String subscriptionDate;
    @Value("${email.th.param.task}")
    private String task;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public boolean sendHtmlMessage(EmployeeDto employeeDto, TaskDto taskDto) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Map<String, Object> properties = new HashMap<>();
            properties.put(employeeName, employeeDto.getFirstName());
            properties.put(projectName, taskDto.getProject().getName());
            properties.put(subscriptionDate, LocalDate.now().toString());
            properties.put(task, taskDto);

            Context context = new Context();
            context.setVariables(properties);

            String[] sendToEmails = new String[]{employeeDto.getEmail(), replyToEmail};
            helper.setFrom(fromEmail);
            helper.setTo(sendToEmails);
            helper.setSubject(subject);
            String html = templateEngine.process(template, context);
            helper.setText(html, true);

            emailSender.send(message);

            return true;
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);

            return false;
        }
    }
}

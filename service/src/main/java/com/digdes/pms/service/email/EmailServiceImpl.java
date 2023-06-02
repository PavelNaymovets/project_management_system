package com.digdes.pms.service.email;

import com.digdes.pms.model.employee.Employee;
import com.digdes.pms.model.task.Task;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${email.send.from}")
    private String fromEmail;
    @Value("${email.reply.to}")
    private String replyToEmail;
    @Value("${email.subject}")
    private String subject;
    @Value("${email.template}")
    private String template;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendHtmlMessage(Employee employee, Task task) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Map<String, Object> properties = new HashMap<>();
            properties.put("name", "Ashish");
            properties.put("subscriptionDate", LocalDate.now().toString());
            properties.put("technologies", Arrays.asList("Python", "Go", "C#"));

            Context context = new Context();
            context.setVariables(properties);

            String[] sendToEmails = new String[]{employee.getEmail(), replyToEmail};
            helper.setFrom(fromEmail);
            helper.setTo(sendToEmails);
            helper.setSubject(subject);
            String html = templateEngine.process(template, context);
            helper.setText(html, true);

            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

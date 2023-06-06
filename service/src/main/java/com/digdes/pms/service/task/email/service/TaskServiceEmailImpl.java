package com.digdes.pms.service.task.email.service;

import com.digdes.pms.dto.employee.EmployeeDto;
import com.digdes.pms.dto.task.TaskDto;
import com.digdes.pms.service.task.email.collector.TaskHtmlLetterCollector;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TaskServiceEmailImpl implements TaskServiceEmail {
    private static final Logger emailLog = LoggerFactory.getLogger("email-log");
    private final JavaMailSender emailSender;
    private final TaskHtmlLetterCollector letterCollector;
    private final MessageSource messageSource;

    @Override
    public boolean sendHtmlMessage(EmployeeDto employeeDto, TaskDto taskDto) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();
            letterCollector.collect(message, helper, context, employeeDto, taskDto);
            emailSender.send(message);
            emailLog.debug(messageSource.getMessage(
                    "email.send.successfully", null, Locale.ENGLISH) + employeeDto.getEmail());

            return true;
        } catch (MessagingException e) {
            emailLog.debug(e.getMessage(), e);

            return false;
        }
    }
}

package com.digdes.pms.service.email.collector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class LetterCollector {
    @Value("${email.send.from}")
    private String fromEmail;
    @Value("${email.reply.to}")
    private String replyToEmail;
    @Value("${email.subject}")
    private String subject;
    @Value("${email.template}")
    private String template;
}

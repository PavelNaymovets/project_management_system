package com.digdes.pms.service.util.collector;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface LetterCollector<T,E> {
    void collect(MimeMessage message,
                 MimeMessageHelper helper,
                 Context context,
                 T toSend,
                 E entitySend) throws MessagingException;
}

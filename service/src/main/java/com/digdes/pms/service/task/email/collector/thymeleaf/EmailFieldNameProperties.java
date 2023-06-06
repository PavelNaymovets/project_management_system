package com.digdes.pms.service.task.email.collector.thymeleaf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailFieldNameProperties {
    private String fromEmail;
    private String replyToEmail;
    private String subject;
    private String thymeleafTemplate;
    private String employeeName;
    private String projectName;
    private String subscriptionDate;
    private String task;
}

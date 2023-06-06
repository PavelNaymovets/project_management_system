package com.digdes.pms.service.util.service;

public interface ServiceEmail<T, E> {
    boolean sendHtmlMessage(T toSend, E entitySend);
}

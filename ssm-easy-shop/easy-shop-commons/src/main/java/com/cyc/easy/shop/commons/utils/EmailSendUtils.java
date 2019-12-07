package com.cyc.easy.shop.commons.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailSendUtils {
    @Autowired
    private SimpleEmail email;

    public void send(String subject, String message,String... to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(message);
        email.addTo(to);
        email.send();
    }
}

package com.cyc.easy.shop.commons.utils;

import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailSendUtils {
    @Autowired
    private SimpleEmail email;
}

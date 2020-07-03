package com.nn.rabbitmq.service;

import com.nn.rabbitmq.entity.Mail;

/**
 * @Author: hyn
 * @Date: 2019-12-29 15:38
 */
public interface TestService {
    Object send(Mail mail);
}

package com.nn.rabbitmq.service;

import com.nn.rabbitmq.config.RabbitMqConfig;
import com.nn.rabbitmq.entity.Mail;
import com.nn.rabbitmq.mapper.MsgLogMapper;
import com.nn.rabbitmq.pojo.MsgLog;
import com.nn.rabbitmq.util.MessageHelper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: hyn
 * @Date: 2019-12-29 15:39
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    MsgLogMapper msgLogMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public Object send(Mail mail) {
        String msgId = UUID.randomUUID().toString().replace("-","");
        mail.setMsgId(msgId);

        MsgLog msgLog = new MsgLog(msgId, mail, RabbitMqConfig.MAIL_EXCHANGE_NAME, RabbitMqConfig.MAIL_ROUTING_KEY_NAME);
        msgLogMapper.insert(msgLog);

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitMqConfig.MAIL_EXCHANGE_NAME, RabbitMqConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);

        return "发送成功";
    }
}

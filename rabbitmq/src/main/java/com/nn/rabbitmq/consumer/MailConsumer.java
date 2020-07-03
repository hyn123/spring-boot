package com.nn.rabbitmq.consumer;

import com.nn.rabbitmq.common.Constant;
import com.nn.rabbitmq.config.RabbitMqConfig;
import com.nn.rabbitmq.entity.Mail;
import com.nn.rabbitmq.pojo.MsgLog;
import com.nn.rabbitmq.service.MsgLogService;
import com.nn.rabbitmq.util.MailUtil;
import com.nn.rabbitmq.util.MessageHelper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: hyn
 * @Date: 2019-12-29 15:50
 */
@Component
@Slf4j
public class MailConsumer {

    @Autowired
    private MsgLogService msgLogService;
    @Autowired
    private MailUtil mailUtil;

    @RabbitListener(queues = RabbitMqConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws Exception {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息: {}", mail.toString());

        String msgId=mail.getMsgId();

        MsgLog msgLog = msgLogService.selectByMsgId(msgId);
        //防止重复消费
        if (null == msgLog || msgLog.getStatus().equals(Constant.MsgLogStatus.CONSUMED_SUCCESS)) {
            log.info("重复消费, msgId: {}", msgId);
            return;
        }

        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();

        boolean success = mailUtil.send(mail);
        if (success) {
            //消息确认，发送成功
            msgLogService.updateStatus(msgId, Constant.MsgLogStatus.CONSUMED_SUCCESS);
            channel.basicAck(tag, false);
        } else {
            //如果发送失败，消息会重新进入队列，再次被这个消费者消费
            //消息发送失败后，也可以将消息发送到别的队列，让其他消费者进行消费
            //第三个参数 true为重新将消息放入队列，如果设置为false，则抛弃这条消息
            channel.basicNack(tag, false, true);
        }
    }

}

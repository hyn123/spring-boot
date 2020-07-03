package com.nn.rabbitmq.config;

import com.nn.rabbitmq.common.Constant;
import com.nn.rabbitmq.mapper.MsgLogMapper;
import com.nn.rabbitmq.pojo.MsgLog;
import com.nn.rabbitmq.util.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hyn
 * @Date: 2019-12-29 13:41
 */
@Configuration
@Slf4j
public class RabbitMqConfig {
    @Autowired
    MsgLogMapper msgLogMapper;

    @Autowired
    private CachingConnectionFactory connectionFactory;


    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        // 消息是否成功发送到Exchange 的回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息成功发送到Exchange");
                String msgId = correlationData.getId();
                //设置消息投递成功
                msgLogMapper.updateStatus(msgId,1);
            } else {
                log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
                String msgId = correlationData.getId();
                //消息投递失败
                msgLogMapper.updateStatus(msgId,2);
            }
        });

        //消息发送到Exchange之后从Exchange 路由到Queue的时候，没有找到Queue就会触发
        // 触发setReturnCallback回调必须设置mandatory=true,
        // 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 消息是否从Exchange路由到Queue,
        // 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        //比如生产者的路由键在这个交换机中当交换机路由到队列时没有对应的队列对应这个路由键
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
        });

        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    // 发送邮件
    public static final String MAIL_QUEUE_NAME = "mail.queue";
    public static final String MAIL_EXCHANGE_NAME = "mail.exchange";
    public static final String MAIL_ROUTING_KEY_NAME = "mail.routing.key";

    @Bean
    public Queue mailQueue() {
        return new Queue(MAIL_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(MAIL_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue())
                .to(mailExchange())
                .with(MAIL_ROUTING_KEY_NAME);
    }

}

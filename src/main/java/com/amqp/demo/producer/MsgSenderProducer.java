package com.amqp.demo.producer;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
@Component
public class MsgSenderProducer {

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void sendSmsCodeRequest2RabbitMq(final String payload) {
        this.rabbitMessagingTemplate.convertAndSend("exchange.smscodesender", "queue.smscodesender", payload);
    }
}

package com.amqp.demo.producer;

import com.amqp.demo.configuration.PropertiesConfiguration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
@Component
public class MsgSenderProducer implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PropertiesConfiguration configuration;


    public void sendSmsCodeRequest2RabbitMq(final String payload) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend("exchange.smscodesender", "quick.orange.rabbit", payload,correlationId);
    }
    public void sendSmsCodeRequest2RabbitMq1(final String payload) {
        this.rabbitTemplate.convertAndSend("exchange.smscodesender", "lazy.pink.rabbit", payload);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(correlationData.getId());
        if (ack){
            System.out.printf("消息投递成功");
        }else {
            System.out.printf("消息投递失败");
        }
    }
}

package com.amqp.demo.callback;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息回掉
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
@Component
public class MsgSenderProducerWithCallBack  implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void sendSmsCodeRequest2RabbitMq(final String payload) {
        this.rabbitMessagingTemplate.convertAndSend("exchange.smscodesender", "queue.smscodesender", payload);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            System.out.printf("成功");
        }else {
            System.out.printf("失败");

        }
    }
}

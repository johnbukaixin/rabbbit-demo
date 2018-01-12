package com.amqp.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
@Component
public class MsgRecevieConsumer {

    //监听队列queue.smscodesender
    @RabbitListener(queues = "queue.smscodesender")
    public void receiveSmsCodeSenderQueue(String payload) {
        System.out.println("Queue [queue.smscodesender] recevie: "+ payload);
    }

    //监听队列queue.demo.smscodesender.demo.demo
    @RabbitListener(queues = "demo.smscodesender.demo.demo")
    public void receiveSmsCodeSenderQueue1(String payload) {
        System.out.println("Queue [demo.smscodesender.demo.demo] recevie: " + payload);

    }
    //监听队列queue.other
    @RabbitListener(queues = "queue.other")
    public void receiveSmsCodeSenderQueue2(String payload) {
        System.out.println("Queue [queue.other] recevie: " + payload);
    }


}

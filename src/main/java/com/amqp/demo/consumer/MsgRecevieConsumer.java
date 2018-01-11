package com.amqp.demo.consumer;

import com.alibaba.fastjson.JSON;
import com.amqp.demo.logic.Notification;
import com.sun.deploy.util.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        System.out.printf(payload);
//        Notification notification = JSON.parseObject(payload,Notification.class);
//        System.out.printf(notification.toString());
    }


}

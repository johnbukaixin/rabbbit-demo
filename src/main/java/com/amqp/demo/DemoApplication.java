package com.amqp.demo;

import com.alibaba.fastjson.JSON;
import com.amqp.demo.payload.Notification;
import com.amqp.demo.payload.RabbitMetaMessage;
import com.amqp.demo.producer.MsgSenderProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private MsgSenderProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		RabbitMetaMessage metaMessage = new RabbitMetaMessage();
		Random random = new Random();

            for (int i = 0 ; i < 1000 ; i++){
                metaMessage.setExchange("exchange.smscodesender");
                metaMessage.setRoutingKey("quick.orange.fox");
                metaMessage.setPayload("quick.orange.fox:" + i);
				Thread.sleep(1000);
                producer.send(metaMessage);
            }

	}
}

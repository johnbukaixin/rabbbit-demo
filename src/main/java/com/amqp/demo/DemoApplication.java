package com.amqp.demo;

import com.alibaba.fastjson.JSON;
import com.amqp.demo.logic.Notification;
import com.amqp.demo.producer.MsgSenderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private MsgSenderProducer senderService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Notification notification = new Notification();
		Random random = new Random();
//		while (true){
			notification.setId(1);
			notification.setMessageId(random.nextInt());
			notification.setPayLoad("hello world");
			String jsonString = JSON.toJSONString(notification);
			senderService.sendSmsCodeRequest2RabbitMq(JSON.toJSONString(jsonString));
			senderService.sendSmsCodeRequest2RabbitMq1(JSON.toJSONString(jsonString));
//		}
	}
}

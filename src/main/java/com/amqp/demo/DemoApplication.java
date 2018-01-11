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
		notification.setId(1);
		notification.setMessageId(1);
		notification.setPayLoad("hello world");
		List<String> payLoads = new ArrayList<>();
		payLoads.add("demo");
		payLoads.add("test");
		notification.setPayloads(payLoads);
		String jsonString = JSON.toJSONString(notification);
		System.out.printf(jsonString);
		senderService.sendSmsCodeRequest2RabbitMq(JSON.toJSONString(jsonString));
	}
}

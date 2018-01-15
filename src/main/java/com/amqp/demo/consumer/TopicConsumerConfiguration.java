package com.amqp.demo.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by panta on 2018/1/15.
 *
 * @author panta
 */
@Configuration
public class TopicConsumerConfiguration {


    @Bean
    public static ChannelAwareMessageListener exampleListener1(){
        return new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String data = new String(message.getBody());
                System.out.println("TOPICTEST1："+data + message.getMessageProperties().getReceivedRoutingKey());
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }
        };
    }

}

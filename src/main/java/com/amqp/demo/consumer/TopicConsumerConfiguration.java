package com.amqp.demo.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
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

                System.out.println(data + " --------------------"+message.getMessageProperties().getRedelivered()+">>>>>>>>queueName:"+ message.getMessageProperties().getConsumerQueue() + ">>>>>>>DeliveryTag:" + message.getMessageProperties().getDeliveryTag());
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }
        };
    }

}

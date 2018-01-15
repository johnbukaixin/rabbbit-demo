package com.amqp.demo.callback;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * created by panta on 2018/1/15.
 *
 * @author panta
 */
public class MsgAckCallBack implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        throw new IllegalArgumentException("Illegal");
    }
}

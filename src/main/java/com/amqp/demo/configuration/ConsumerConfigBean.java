package com.amqp.demo.configuration;

import com.amqp.demo.consumer.TopicConsumerConfiguration;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
@Configuration
@EnableRabbit
public class ConsumerConfigBean implements RabbitListenerConfigurer{

    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

//    /**
//     * queue listener 观察 监听模式 当有消息到达时会通知监听在对应的队列上的监听对象
//     * @param connectionFactory
//     * @return
//     */
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
//            ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        // factory.setPrefetchCount(5);//指定一个请求能处理多少个消息，如果有事务的话，必须大于等于transaction数量.
////        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
//
//        //MANUAL：将ACK修改为手动确认，避免消息在处理过程中发生异常造成被误认为已经成功消费的假象。
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//
//        return factory;
//    }
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }


    /**
     * queue listener 观察 监听模式 当有消息到达时会通知监听在对应的队列上的监听对象
     * @param connectionFactory
     * @return
     */
    @Bean("topicTest1Container")
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("queue.smscodesender","queue.other");
        container.setMessageListener(TopicConsumerConfiguration.exampleListener1());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }


}

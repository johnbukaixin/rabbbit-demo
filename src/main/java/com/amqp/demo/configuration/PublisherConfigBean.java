package com.amqp.demo.configuration;

import com.amqp.demo.constants.MQConstants;
import com.amqp.demo.payload.RabbitMetaMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
@Configuration
public class PublisherConfigBean {

    private Logger logger = LoggerFactory.getLogger(PublisherConfigBean.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * rabbitAdmin 用于管理 exchanges, queues and bindings等
     */
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

//    /**
//     * queue 声明队列：queue.smscodesender
//     * @param rabbitAdmin
//     * @return
//     */
//    @Bean
//    Queue queueSmsCodeSender(RabbitAdmin rabbitAdmin) {
//        Queue queue = new Queue("queue.smscodesender", true);
//        rabbitAdmin.declareQueue(queue);
//        return queue;
//    }
//
//
//    /**
//     * exchange 声明交换：exchange.smscodesender
//     * @param rabbitAdmin
//     * @return
//     */
//    @Bean
//    TopicExchange exchange(RabbitAdmin rabbitAdmin) {
//        TopicExchange topicExchange = new TopicExchange("exchange.smscodesender");
//        rabbitAdmin.declareExchange(topicExchange);
//        return topicExchange;
//    }

//
//    /**
//     * 绑定exchange和queue
//     * @param queueSmsCodeSender2
//     * @param exchange
//     * @param rabbitAdmin
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeSmsCodeSender1(Queue queueSmsCodeSender2, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
//        Binding binding = BindingBuilder.bind(queueSmsCodeSender2).to(exchange).with("*.*.rabbit");
//        rabbitAdmin.declareBinding(binding);
//        return binding;
//    }
//


    /**
     * 声明 spring template
     * @param
     * @return
     */
    @Bean
    public RabbitTemplate customRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        // mandatory 必须设置为true，ReturnCallback才会调用
        rabbitTemplate.setMandatory(true);
        // 消息发送到RabbitMQ交换器后接收ack回调
        setConfirmCallback(rabbitTemplate);
        setReturnCallback(rabbitTemplate);
        return rabbitTemplate;
    }

    /**
     * 设置回调
     * @param rabbitTemplate
     */
    private void setReturnCallback(RabbitTemplate rabbitTemplate) {
        //消息发送到RabbitMQ交换器，但无相应Exchange时的回调
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String cacheKey = message.getMessageProperties().getMessageId();

            logger.error("return回调，没有找到任何匹配的队列！message id:{},replyCode{},replyText:{},"
                    + "exchange:{},routingKey{}", cacheKey, replyCode, replyText, exchange, routingKey);

            RabbitMetaMessage metaMessage = (RabbitMetaMessage) redisTemplate.opsForHash().get(MQConstants.MQ_PRODUCER_RETRY_KEY, cacheKey);

            metaMessage.setReturnCallback(true);

            // 由于amqp奇葩协议规定，return比confirm先回调
            redisTemplate.opsForHash().put(MQConstants.MQ_PRODUCER_RETRY_KEY, cacheKey, metaMessage);
        });
    }

    /**
     * 设置回调
     * @param rabbitTemplate
     */
    private void setConfirmCallback(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

            logger.debug("confirm回调，ack={} correlationData={} cause={}", ack, correlationData, cause);

            String cacheKey = correlationData.getId();
            RabbitMetaMessage metaMessage = (RabbitMetaMessage) redisTemplate.opsForHash().get(MQConstants.MQ_PRODUCER_RETRY_KEY, cacheKey);
            // 只要消息能投入正确的交换器中，ack就为true
            if (ack) {
                if (!metaMessage.isReturnCallback()) {
                    logger.info("消息已正确投递到队列，correlationData:{}", correlationData);
                    // 清除重发缓存
                    redisTemplate.opsForHash().delete(MQConstants.MQ_PRODUCER_RETRY_KEY, cacheKey);
                } else {
                    logger.warn("交换机投机消息至队列失败，correlationData:{}", correlationData);
                }
            } else {
                logger.error("消息投递至交换机失败，correlationData:{}，原因：{}", correlationData, cause);
                if (!metaMessage.isAutoTrigger()) {
                    metaMessage.setAutoTrigger(true);
                    try {
                        redisTemplate.opsForHash().put(MQConstants.MQ_PRODUCER_RETRY_KEY, cacheKey, objectMapper.writeValueAsString(metaMessage));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 消息对象json转换类
     * @return
     */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        return jsonMessageConverter;
    }
}

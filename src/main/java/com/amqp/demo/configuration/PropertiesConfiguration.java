package com.amqp.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置消费者监听队列
 * created by panta on 2018/1/17.
 *
 * @author panta
 */
@Component
@ConfigurationProperties(prefix = "demo.config.*")
public class PropertiesConfiguration {

    private String exchangeName;

    private String queueName;

    private String routingKey;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    /**
     * 将获取到的配置文件中以空格隔开的多个队列名称，解析后存入list
     * @param configQueueName 配置文件中的队列值
     * @return
     */
    public static String[] parseConfigQueueName(String configQueueName){
        String[] queues = configQueueName.split(" ");
        return queues;
    }

    public static String[] parseConfigExchangeName(String exchangeName){
        String[] exchanges = exchangeName.split(" ");
        return exchanges;
    }
}

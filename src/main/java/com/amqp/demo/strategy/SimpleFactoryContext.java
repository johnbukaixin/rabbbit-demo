package com.amqp.demo.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SimpleFactoryContext {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleFactoryContext.class);
    private static Map<String, Class> allStrategies;

    private String identity;

    static {
        //在这里使用反射，获取使用注解了的策略方式

    }

    private AbstractSendMessageStrategy strategy;

    public SimpleFactoryContext(String identity) {
      //通过标志 识别采用哪一种策略

    }

    public void action(BaseSendMessageParam param) {
        strategy.send(param);
    }

}

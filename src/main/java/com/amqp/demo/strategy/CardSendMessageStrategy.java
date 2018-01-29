package com.amqp.demo.strategy;

import org.springframework.stereotype.Component;

/**
 * created by panta on 2018/1/24.
 *
 * @author panta
 */
@StrategyAnnotation(value = "cardSendMessageStrategy")
@Component
public class CardSendMessageStrategy extends AbstractSendMessageStrategy {
    @Override
    protected void build(BaseSendMessageParam param) {

    }

    @Override
    protected void sendMessage(BaseSendMessageParam param) {

    }
}

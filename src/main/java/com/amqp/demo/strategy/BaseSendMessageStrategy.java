package com.amqp.demo.strategy;

/**
 * created by panta on 2018/1/24.
 *
 * @author panta
 */
public interface BaseSendMessageStrategy {

    /**
     * 发短信的基本方法
     * @param param
     */
    void send(BaseSendMessageParam param);
}

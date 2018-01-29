package com.amqp.demo.strategy;

/**
 * created by panta on 2018/1/24.
 *
 * @author panta
 */
public abstract class AbstractSendMessageStrategy implements BaseSendMessageStrategy {

    @Override
    public void send(BaseSendMessageParam param) {
        build(param);
        sendMessage(param);
    }

    /**
     * 构建参数
     */
    protected abstract void build(BaseSendMessageParam param);

    /**
     * 发短信的真实方法
     */
    protected  abstract  void sendMessage(BaseSendMessageParam param);

}

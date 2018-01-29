package com.amqp.demo.payload;

import java.io.Serializable;

/**
 * created by panta on 2018/1/22.
 * @author panta
 */
public class RabbitMetaMessage implements Serializable{

    private static final long serialVersionUID = 8789119712886970095L;
    private String exchange;
    private String routingKey;
    private boolean autoTrigger;
    private boolean returnCallback;
    private Object payload;

    public Object getPayload() {
        return payload;
    }
    public void setPayload(Object payload) {
        this.payload = payload;
    }
    public boolean isReturnCallback() {
        return returnCallback;
    }
    public void setReturnCallback(boolean returnCallback) {
        this.returnCallback = returnCallback;
    }
    public String getExchange() {
        return exchange;
    }
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    public String getRoutingKey() {
        return routingKey;
    }
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
    public boolean isAutoTrigger() {
        return autoTrigger;
    }
    public void setAutoTrigger(boolean autoTrigger) {
        this.autoTrigger = autoTrigger;
    }



}

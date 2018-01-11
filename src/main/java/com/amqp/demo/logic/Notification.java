package com.amqp.demo.logic;

import java.util.List;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
public class Notification {

    private Integer id;

    private Integer messageId;

    private String properties;

    private String payLoad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }
}

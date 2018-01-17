package com.amqp.demo.payload;

import java.io.Serializable;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
public class Notification implements Serializable{


    private static final long serialVersionUID = 1745053020383632812L;

    private Integer id;

    private Integer messageId;


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


    public String getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", messageId=" + messageId +
                ", payLoad='" + payLoad + '\'' +
                '}';
    }

    public Notification() {
    }


}

package com.amqp.demo.payload;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * created by panta on 2018/1/11.
 *
 * @author panta
 */
@Data
@ToString
public class Notification implements Serializable{


    private static final long serialVersionUID = 1745053020383632812L;

    private Integer id;

    private Integer messageId;


    private String payLoad;


}

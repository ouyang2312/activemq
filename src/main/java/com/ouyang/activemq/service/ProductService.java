package com.ouyang.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author oy
 * @description
 * @date 2019/10/12
 */
@Component
public class ProductService {

    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * 发送消息给对应的目的地
     * @author oy
     * @date 2019/10/12
     * @param destination, message
     * @return void
     */
    public void sendMessage(Destination destination, String message){
        jmsTemplate.convertAndSend(destination,message);
    }

}

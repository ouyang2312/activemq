package com.ouyang.activemq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author oy
 * @description
 * @date 2019/10/12
 */
@Component
public class ConsumerService {

    @JmsListener(destination = "${queue.oy}")
    public void receiveQuery(String msg){
        System.out.println("接受到的消息："+msg);
    }
//121636

    //这是

    //第二次
}

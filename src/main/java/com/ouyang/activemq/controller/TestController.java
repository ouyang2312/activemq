package com.ouyang.activemq.controller;

import com.ouyang.activemq.service.ProductService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * @author oy
 * @description
 * @date 2019/10/12
 */
@RestController
@RequestMapping("/activeMQ")
public class TestController {

    @Autowired
    private ProductService productService;

    @Value("${queue.oy}")
    private String queue;

    @GetMapping(value = "/commonSendMsg")
    public void commonSendMsg(@RequestParam("msg") String msg){
        Destination destination = new ActiveMQQueue(queue);
        productService.sendMessage(destination,msg);
    }

    @GetMapping(value = "/delaySendMsg")
    public void delaySendMsg(@RequestParam("msg") String msg){
        Destination destination = new ActiveMQQueue(queue);
        productService.delaySend(destination,msg,1000L*20);
    }

    public void test(){

    }

}

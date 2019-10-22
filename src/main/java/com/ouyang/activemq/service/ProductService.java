package com.ouyang.activemq.service;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.Serializable;

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


    /**
     * 延时发送
     * @author oy
     * @date 2019/10/22
     * @param destination, data, time
     * @return void
     */
    public <T extends Serializable>  void delaySend(Destination destination, T data, Long time){
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        // 获取连接工厂
        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
        try {
            // 获取连接
            connection = connectionFactory.createConnection();
            connection.start();
            // 获取session，true开启事务，false关闭事务
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            producer = session.createProducer(destination);
            producer.setDeliveryMode(JmsProperties.DeliveryMode.PERSISTENT.getValue());
            ObjectMessage message = session.createObjectMessage(data);
            //设置延迟时间
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
            // 发送消息
            producer.send(message);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (producer != null){
                    producer.close();
                }
                if (session != null){
                    session.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}

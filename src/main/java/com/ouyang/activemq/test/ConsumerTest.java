package com.ouyang.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author oy
 * @description
 * @date 2019/10/10
 */
public class ConsumerTest {

    private static String url = "tcp://119.23.187.31:61616";

    public static void main(String[] args) throws Exception {
        //创建连接工厂对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //获取连接对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();

        //使用连接对象获取Session对象,并设置参数
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //使用Session对象创建Destination对象,其中参数为：消息队列的名称
        Queue queue = session.createQueue("ouyang");

        //创建消息消费者对象
        MessageConsumer consumer = session.createConsumer(queue);

        //接收消息 MessageListener
        consumer.setMessageListener((Message message)->{
            //简单打印一下
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        //接收键盘输入，当在控制台输入回车时结束。（为了让该方法一直处于执行状态）
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

}

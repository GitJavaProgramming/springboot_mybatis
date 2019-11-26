package org.pp.springboot_mybatis.mq.rabbitMq.direct;

import org.pp.springboot_mybatis.mq.rabbitMq.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String message = "message=" + new Date();
        System.out.println("Sender  " + message);
        rabbitTemplate.convertAndSend("immediate_exchange_test1", "immediate_routing_key_test1", message);
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirectQueue() {
        User user = new User();
        user.setUserId("123456");
        user.setName("lizhencheng");
        System.out.println("【sendDirectQueue已发送消息】");
        // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
        this.amqpTemplate.convertAndSend(RabbitmqConfig.QUEUE, user);
    }
}
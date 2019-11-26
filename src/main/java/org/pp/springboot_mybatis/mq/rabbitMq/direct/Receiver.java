package org.pp.springboot_mybatis.mq.rabbitMq.direct;

import org.pp.springboot_mybatis.mq.rabbitMq.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitHandler
    @RabbitListener(queues = RabbitmqConfig.QUEUE)
    public void immediateProcess(String message) {
        System.out.println("Receiver" + message);
    }

    //     queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitmqConfig.QUEUE)
    public void receiverDirectQueue(User user) {
        System.out.println("【receiverDirectQueue监听到消息】" + user.toString());
    }
}

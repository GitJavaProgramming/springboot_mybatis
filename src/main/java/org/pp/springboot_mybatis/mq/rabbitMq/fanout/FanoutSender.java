package org.pp.springboot_mybatis.mq.rabbitMq.fanout;

import org.pp.springboot_mybatis.mq.rabbitMq.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendFanout() {
        User user = new User();
        user.setUserId("123456");
        user.setName("lizhencheng");
        System.out.println("【sendFanout已发送消息】");
        // 注意， 这里的第2个参数为空。
        // 因为fanout 交换器不处理路由键，只是简单的将队列绑定到交换器上，
        // 每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上
        this.amqpTemplate.convertAndSend(RabbitMQFanoutConfig.FANOUT_EXCHANGE, "", user);
    }

}
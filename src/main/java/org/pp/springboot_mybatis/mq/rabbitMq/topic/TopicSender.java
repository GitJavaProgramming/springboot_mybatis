package org.pp.springboot_mybatis.mq.rabbitMq.topic;

import org.pp.springboot_mybatis.mq.rabbitMq.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendTopic() {
        User user1 = new User();
        user1.setUserId("123456");
        user1.setName("pengpeng");

        User user2 = new User();
        user2.setUserId("456789");
        user2.setName("张三");

        System.out.println("【sendTopic已发送消息】");
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitmqTopicConfig.TOPIC_EXCHANGE, "pp.message", user1);
        this.amqpTemplate.convertAndSend(RabbitmqTopicConfig.TOPIC_EXCHANGE, "pp.pp", user2);
    }

}
package org.pp.springboot_mybatis.controller;

import org.pp.context.IgnoreScanBean;
import org.pp.springboot_mybatis.mq.rabbitMq.direct.Sender;
import org.pp.springboot_mybatis.mq.rabbitMq.fanout.FanoutSender;
import org.pp.springboot_mybatis.mq.rabbitMq.topic.TopicSender;
import org.pp.springboot_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@IgnoreScanBean
@RestController
@RequestMapping("/testBoot")
public class UserController {

    @Autowired
    private UserService userService;

    /* =============================================rabbitMQ============================================= */
    @Autowired
    private Sender sender;

    @Autowired
    private TopicSender sendTopic;

    @Autowired
    private FanoutSender fanoutSender;

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id) {
        return userService.Sel(id).toString();
    }

    @GetMapping("/sendDirectQueue")
    public Object sendDirectQueue() {
        sender.sendDirectQueue();
        return "ok";
    }

    @GetMapping("/immediateProcess")
    public Object immediateProcess() {
        sender.send();
        return "ok";
    }

    @GetMapping("/sendTopic")
    public Object sendTopic() {
        sendTopic.sendTopic();
        return "ok";
    }

    @GetMapping("/sendFanout")
    public Object sendFanout() {
        fanoutSender.sendFanout();
        return "ok";
    }

    /* =============================================kafka============================================= */
    @Autowired
    private KafkaTemplate kafkaTemplate;

    // test: http://localhost:8080/testBoot/kafka/send?message=222
    @GetMapping("/kafka/send")
    public boolean send(@RequestParam String message) {
        kafkaTemplate.send("testTopic", message);
        return true;
    }

}
package org.pp.springboot_mybatis.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    @KafkaListener(topics = "testTopic")
    public void listen(ConsumerRecord<?, String> record) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
    }

}

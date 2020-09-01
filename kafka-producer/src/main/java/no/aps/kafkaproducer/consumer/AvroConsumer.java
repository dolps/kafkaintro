package no.aps.kafkaproducer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AvroConsumer {
    @KafkaListener(topics = "employeeTopic", groupId = "foo")
    public void listenGroupFoo(final String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}

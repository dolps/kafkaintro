package no.aps.kafkaproducer.consumer;

import no.aps.schema.Employee;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class AvroConsumer {
    /*
    @KafkaListener(topics = "employeeTopic", groupId = "foo")
    public void listenGroupFoo(final ConsumerRecord<String, Employee> employeeConsumerRecord) {
        System.out.println("Received Message in group foo: " + employeeConsumerRecord.value().getLastName());
    }

     */
    @KafkaListener(topics = "employeeTopic", groupId = "foo", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(final Employee employeeConsumerRecord) {
        System.out.println("Received Message in group foo: " + employeeConsumerRecord.getLastName());
    }
}

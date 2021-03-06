package no.aps.kafkaproducer.producer;

import no.aps.schema.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class AvroProducer {
    private final KafkaTemplate<String, Employee> kafkaTemplate;
    private final String topicName = "employeeTopic";

    @Autowired
    public AvroProducer(final KafkaTemplate<String, Employee> kafkaTemplate) {this.kafkaTemplate = kafkaTemplate;}

    public void sendMessage(final Employee employee) {
        final ListenableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(topicName, employee.getFirstName(), employee);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(final SendResult<String, Employee> result) {
                System.out.println("Sent message=[" + employee + "] with offset=[" + result.getRecordMetadata()
                                                                                      .offset() + "]");
            }

            @Override
            public void onFailure(final Throwable ex) {
                System.out.println("Unable to send message=[" + employee + "] due to : " + ex.getMessage());
            }
        });
    }

    /*
    public void sendMessage(final String msg) {
        final ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, msg);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(final SendResult<String, String> result) {
                System.out.println("Sent message=[" + msg + "] with offset=[" + result.getRecordMetadata()
                                                                                      .offset() + "]");
            }

            @Override
            public void onFailure(final Throwable ex) {
                System.out.println("Unable to send message=[" + msg + "] due to : " + ex.getMessage());
            }
        });
    }

     */

    public void produceEmployeeDetails(final int empId, final String firstName, final String lastName) {

        // creating employee details
        final Employee employee = new Employee();
        employee.setId(empId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        final Message<Employee> message = MessageBuilder.withPayload(employee)
                                                        .build();
/*
        processor.output()
                 .send(message);

 */
    }
}

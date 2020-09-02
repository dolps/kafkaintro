package no.aps.kafkaproducer.rest;

import no.aps.kafkaproducer.producer.AvroProducer;
import no.aps.schema.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerRest {
    private final AvroProducer avroProducer;

    @Autowired
    public ProducerRest(final AvroProducer avroProducer) {this.avroProducer = avroProducer;}

    @GetMapping("/produce")
    public String produce(final String msg) {
        avroProducer.sendMessage(new Employee(1, msg, msg));

        return msg + "sendt";
    }
}

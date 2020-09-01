package no.aps.kafkaproducer.rest;

import no.aps.kafkaproducer.producer.AvroProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerRest {
    private final AvroProducer avroProducer;

    public ProducerRest(final AvroProducer avroProducer) {this.avroProducer = avroProducer;}

    @GetMapping("/produce")
    public String produce(final String msg) {
        avroProducer.sendMessage(msg);

        return msg + "sendt";
    }
}

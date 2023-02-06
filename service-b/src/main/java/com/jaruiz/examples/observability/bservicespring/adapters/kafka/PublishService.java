package com.jaruiz.examples.observability.bservicespring.adapters.kafka;

import com.jaruiz.examples.observability.bservicespring.adapters.kafka.dto.ProcessDataMessage;
import com.jaruiz.examples.observability.bservicespring.business.model.ProcessData;
import com.jaruiz.examples.observability.bservicespring.business.ports.ProcessPublishPort;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublishService implements ProcessPublishPort {

    @Value("${service-b.topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, ProcessDataMessage> kafkaTemplate;

    @Override public void publish(ProcessData processData) {
        send(bm2Message(processData));
    }

    @WithSpan(value = "Kafka Adapter: publish")
    private void send(@SpanAttribute("processData") ProcessDataMessage processDataMessage) {
        kafkaTemplate.setObservationEnabled(true);
        kafkaTemplate.send(topicName, processDataMessage);
    }
    private ProcessDataMessage bm2Message(ProcessData processData) {
        return new ProcessDataMessage(processData.getId(), processData.getInitValue(), processData.getCurrentValue());
    }
}

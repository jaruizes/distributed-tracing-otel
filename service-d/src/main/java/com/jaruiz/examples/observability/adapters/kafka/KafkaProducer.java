package com.jaruiz.examples.observability.adapters.kafka;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jaruiz.examples.observability.adapters.kafka.dto.ProcessDataMessage;
import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.ProcessPublishPort;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class KafkaProducer implements ProcessPublishPort {

    @Inject
    @Channel("service-d") Emitter<ProcessDataMessage> processDataEmitter;

    @Override public void publish(ProcessData processData) {
        processDataEmitter.send(BM2Message(processData));
    }

    private ProcessDataMessage BM2Message(ProcessData processData) {
        return new ProcessDataMessage(processData.getId(), processData.getInitValue(), processData.getCurrentValue());
    }
}

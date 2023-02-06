package com.jaruiz.examples.observability.api.async;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jaruiz.examples.observability.api.async.dto.ProcessDataMessage;
import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.CServiceBusinessPort;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class ProcessConsumer {

    @Inject CServiceBusinessPort service;

    @Incoming("topic-b")
    @WithSpan(value = "Kafka Adapter: consumer")
    public void consume(@SpanAttribute("data") ProcessDataMessage processDataMessage) {
        service.updateProcess(messageToBM(processDataMessage));
    }

    private ProcessData messageToBM(ProcessDataMessage processDataMessage) {
        return new ProcessData(processDataMessage.getId(), processDataMessage.getInitValue(), processDataMessage.getCurrentValue());
    }
}

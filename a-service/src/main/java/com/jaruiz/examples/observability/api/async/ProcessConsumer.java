package com.jaruiz.examples.observability.api.async;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jaruiz.examples.observability.api.async.dto.ProcessDataMessage;
import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.AServiceBusinessPort;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class ProcessConsumer {

    @Inject AServiceBusinessPort aService;

    @Blocking
    @Incoming("topic-d")
    @WithSpan("consume-A")
    public void consume(ProcessDataMessage processDataMessage) {
        aService.saveProcess(messageToBM(processDataMessage));
    }

    private ProcessData messageToBM(ProcessDataMessage processDataMessage) {
        return new ProcessData(processDataMessage.getId(), processDataMessage.getInitValue(), processDataMessage.getCurrentValue());
    }
}

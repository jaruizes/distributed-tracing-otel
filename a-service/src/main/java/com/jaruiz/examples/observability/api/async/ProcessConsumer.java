package com.jaruiz.examples.observability.api.async;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jaruiz.examples.observability.api.async.dto.ProcessDataMessage;
import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.AServiceBusinessPort;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class ProcessConsumer {

    @Inject AServiceBusinessPort aService;

    @Incoming("topic-d")
    public void consume(ProcessDataMessage processDataMessage) {
        aService.saveProcess(messageToBM(processDataMessage));
    }

    private ProcessData messageToBM(ProcessDataMessage processDataMessage) {
        return new ProcessData(processDataMessage.getId(), processDataMessage.getInitValue(), processDataMessage.getCurrentValue());
    }
}

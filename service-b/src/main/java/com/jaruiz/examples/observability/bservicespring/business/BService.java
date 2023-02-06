package com.jaruiz.examples.observability.bservicespring.business;

import com.jaruiz.examples.observability.bservicespring.adapters.kafka.PublishService;
import com.jaruiz.examples.observability.bservicespring.business.model.ProcessData;
import com.jaruiz.examples.observability.bservicespring.business.ports.BServiceBusinessPort;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BService implements BServiceBusinessPort {

    @Autowired
    private PublishService publishService;

    @WithSpan(value = "Business layer: updateProcess")
    public ProcessData updateProcess(ProcessData processData) {
        if (processData.getCurrentValue() < 0) {
            throw new RuntimeException("Esto es un fallo deliberado para mostrar el error en las trazas");
        }

        final ProcessData processDataUpdated = new ProcessData(processData.getId(), processData.getInitValue(), processData.getCurrentValue() + 10);
        publishService.publish(processDataUpdated);

        return processDataUpdated;
    }
}

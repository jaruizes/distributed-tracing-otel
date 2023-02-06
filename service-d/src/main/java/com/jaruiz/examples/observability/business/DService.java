package com.jaruiz.examples.observability.business;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.DServiceBusinessPort;
import com.jaruiz.examples.observability.business.ports.ProcessPublishPort;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@ApplicationScoped
public class DService implements DServiceBusinessPort {

    @Inject
    private ProcessPublishPort publishService;

    @WithSpan(value="Business layer")
    public ProcessData updateProcess(ProcessData processData) {
        final ProcessData processDataUpdated = new ProcessData(processData.getId(), processData.getInitValue(), processData.getCurrentValue() + 10);
        publishService.publish(processDataUpdated);

        return processDataUpdated;
    }
}

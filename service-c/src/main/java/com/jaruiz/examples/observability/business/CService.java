package com.jaruiz.examples.observability.business;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.CServiceBusinessPort;
import com.jaruiz.examples.observability.business.ports.ServiceDPort;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@ApplicationScoped
public class CService implements CServiceBusinessPort {

    @Inject ServiceDPort serviceD;
    @WithSpan(value = "Business layer: updateProcess")
    public void updateProcess(ProcessData processData) {
        final ProcessData processDataUpdated = new ProcessData(processData.getId(), processData.getInitValue(), processData.getCurrentValue() + 10);

        serviceD.callServiceD(processDataUpdated);
    }
}

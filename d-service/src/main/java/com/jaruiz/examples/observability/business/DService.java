package com.jaruiz.examples.observability.business;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.DServiceBusinessPort;
import com.jaruiz.examples.observability.business.ports.ProcessPublishPort;

@ApplicationScoped
public class DService implements DServiceBusinessPort {

    @Inject
    private ProcessPublishPort publishService;

    public ProcessData updateProcess(ProcessData processData) {
        final ProcessData processDataUpdated = new ProcessData(processData.getId(), processData.getInitValue(), processData.getCurrentValue() + 10);
        publishService.publish(processDataUpdated);

        return processDataUpdated;
    }
}

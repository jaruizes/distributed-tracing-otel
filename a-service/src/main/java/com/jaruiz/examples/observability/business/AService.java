package com.jaruiz.examples.observability.business;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;

import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.AServiceBusinessPort;
import com.jaruiz.examples.observability.business.ports.ProcessPersistencePort;
import com.jaruiz.examples.observability.business.ports.ServiceBPort;

@ApplicationScoped
public class AService implements AServiceBusinessPort {

    @Inject ServiceBPort serviceB;
    @Inject ProcessPersistencePort processPersistenceService;

    public long initProcess(int initValue) {
        long processId = Instant.now().toEpochMilli();
        var processData = new ProcessData(processId, initValue, initValue);
        serviceB.callServiceB(processData);

        return processId;
    }

    public void saveProcess(ProcessData processData) {
        processPersistenceService.save(processData);
    }
}

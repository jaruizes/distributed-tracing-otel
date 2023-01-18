package com.jaruiz.examples.observability.adapters.persistence;

import javax.enterprise.context.ApplicationScoped;

import com.jaruiz.examples.observability.adapters.persistence.model.ProcessDataPM;
import com.jaruiz.examples.observability.adapters.persistence.repository.ProcessRepository;
import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.ProcessPersistencePort;

@ApplicationScoped
public class ProcessPersistenceService implements ProcessPersistencePort {

    private final ProcessRepository processRepository;

    public ProcessPersistenceService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Override
    public void save(ProcessData processData) {
        processRepository.save(buildPM(processData));
    }

    private ProcessDataPM buildPM(ProcessData processData) {
        final ProcessDataPM processDataPM = new ProcessDataPM();
        processDataPM.setId(processData.getId());
        processDataPM.setInitValue(processData.getInitValue());
        processDataPM.setFinalValue(processData.getFinalValue());

        return processDataPM;
    }
}

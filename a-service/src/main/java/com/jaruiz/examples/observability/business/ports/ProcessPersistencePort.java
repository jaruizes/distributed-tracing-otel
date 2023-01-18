package com.jaruiz.examples.observability.business.ports;

import com.jaruiz.examples.observability.business.model.ProcessData;

public interface ProcessPersistencePort {
    void save(ProcessData processData);
}

package com.jaruiz.examples.observability.business.ports;

import com.jaruiz.examples.observability.business.model.ProcessData;

public interface AServiceBusinessPort {
    long initProcess(int initValue);
    void saveProcess(ProcessData processData);
}

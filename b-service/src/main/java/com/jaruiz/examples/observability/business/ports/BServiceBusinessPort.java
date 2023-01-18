package com.jaruiz.examples.observability.business.ports;

import com.jaruiz.examples.observability.business.model.ProcessData;

public interface BServiceBusinessPort {
    ProcessData updateProcess(ProcessData processData);
}

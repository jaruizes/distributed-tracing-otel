package com.jaruiz.examples.observability.adapters.extservices.serviceb;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.jaruiz.examples.observability.adapters.extservices.serviceb.client.ServiceBClient;
import com.jaruiz.examples.observability.adapters.extservices.serviceb.client.dto.ProcessDataDTO;
import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.ServiceBPort;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ServiceB implements ServiceBPort {

    @Inject
    @RestClient
    ServiceBClient serviceBClient;

    @Override public ProcessData callServiceB(ProcessData processData) {
        final Response response = serviceBClient.updateProcess(bm2DTO(processData));
        var processDataResponse = response.readEntity(ProcessDataDTO.class);

        return dto2BM(processDataResponse);
    }

    private ProcessDataDTO bm2DTO(ProcessData processData) {
        return new ProcessDataDTO(processData.getId(), processData.getInitValue(), processData.getFinalValue());
    }

    private ProcessData dto2BM(ProcessDataDTO processDataDTO) {
        return new ProcessData(processDataDTO.getId(), processDataDTO.getInitialValue(), processDataDTO.getCurrentValue());
    }
}

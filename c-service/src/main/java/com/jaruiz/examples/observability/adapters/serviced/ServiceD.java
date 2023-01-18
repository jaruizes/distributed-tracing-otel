package com.jaruiz.examples.observability.adapters.serviced;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.jaruiz.examples.observability.adapters.serviced.client.ServiceDClient;
import com.jaruiz.examples.observability.adapters.serviced.client.dto.ProcessDataDTO;
import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.ServiceDPort;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ServiceD implements ServiceDPort {

    @Inject
    @RestClient
    ServiceDClient serviceDClient;

    @Override public ProcessData callServiceB(ProcessData processData) {
        final Response response = serviceDClient.updateProcess(bm2DTO(processData));

        return dto2BM((ProcessDataDTO) response.getEntity());
    }

    private ProcessDataDTO bm2DTO(ProcessData processData) {
        return new ProcessDataDTO(processData.getId(), processData.getInitValue(), processData.getCurrentValue());
    }

    private ProcessData dto2BM(ProcessDataDTO processDataDTO) {
        return new ProcessData(processDataDTO.getId(), processDataDTO.getInitialValue(), processDataDTO.getCurrentValue());
    }
}

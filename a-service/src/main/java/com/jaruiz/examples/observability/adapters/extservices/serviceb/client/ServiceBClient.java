package com.jaruiz.examples.observability.adapters.extservices.serviceb.client;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.jaruiz.examples.observability.adapters.extservices.serviceb.client.dto.ProcessDataDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/b-service")
@RegisterRestClient(configKey="serviceb-api")
public interface ServiceBClient {

    @POST Response updateProcess(final ProcessDataDTO processDataDTO);
}

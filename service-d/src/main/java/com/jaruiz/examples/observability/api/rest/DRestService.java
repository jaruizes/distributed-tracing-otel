package com.jaruiz.examples.observability.api.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import com.jaruiz.examples.observability.api.rest.dto.ProcessDataDTO;
import com.jaruiz.examples.observability.business.model.ProcessData;
import com.jaruiz.examples.observability.business.ports.DServiceBusinessPort;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@Path("/d-service")
public class DRestService {

    @Inject DServiceBusinessPort dService;

    @Context
    UriInfo uriInfo;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @WithSpan(value = "API Rest: POST Endpoint")
    public Response updateProcess(@SpanAttribute("data") final ProcessDataDTO processDataDTO) {
        final ProcessData processData = dService.updateProcess(dto2BM(processDataDTO));

        return Response.created(UriBuilder.fromUri(this.uriInfo.getPath())
                                          .path(String.valueOf(processDataDTO.getId()))
                                          .build()).entity(BM2DTO(processData)).build();

    }

    private ProcessData dto2BM(ProcessDataDTO processDataDTO) {
        return new ProcessData(processDataDTO.getId(), processDataDTO.getInitialValue(), processDataDTO.getCurrentValue());
    }

    private ProcessDataDTO BM2DTO(ProcessData processData) {
        return new ProcessDataDTO(processData.getId(), processData.getInitValue(), processData.getCurrentValue());
    }
}

package com.jaruiz.examples.observability.api.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.jaruiz.examples.observability.api.rest.dto.InitProcessDTO;
import com.jaruiz.examples.observability.api.rest.dto.ResultDTO;
import com.jaruiz.examples.observability.business.ports.AServiceBusinessPort;

@Path("/a-service")
public class ARestService {

    @Inject
    AServiceBusinessPort aService;

    @Context
    UriInfo uriInfo;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response initProcess(final InitProcessDTO initProcessDTO) {
        final Long processId = aService.initProcess(initProcessDTO.getInitialValue());

        return Response.created(UriBuilder.fromUri(this.uriInfo.getPath())
                                          .path(String.valueOf(processId))
                                          .build()).entity(initProcessDTO).build();

    }

//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ResultDTO getResult(@PathParam("id") long processId) {
//        return "Hello RESTEasy";
//    }
}

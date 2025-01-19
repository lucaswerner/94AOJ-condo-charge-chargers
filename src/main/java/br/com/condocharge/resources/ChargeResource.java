package br.com.condocharge.resources;

import java.util.List;

import br.com.condocharge.dto.ChargeDTO;
import br.com.condocharge.dto.PostChargeDTO;
import br.com.condocharge.service.ChargeService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/charge")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChargeResource {

    @Inject
    ChargeService chargeService;

    @GET
    public List<ChargeDTO> getAll() {
        return chargeService.getAll();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return chargeService.getById(id)
                .map(charge -> Response.ok(charge).build())
                .orElse(Response.status(404).build());
    }

    @POST
    public Response postStation(@Valid PostChargeDTO postedCharge, @Context UriInfo uriInfo) {
        final ChargeDTO registeredCharge = chargeService.registerNewCharge(postedCharge);
        return Response
                .created(uriInfo
                        .getAbsolutePathBuilder()
                        .path(registeredCharge.getChargeId().toString())
                        .build())
                .entity(registeredCharge)
                .build();
    }

    @PATCH
    @Path("{id}")
    public Response registerEnd(@PathParam("id") Long id) {
        return chargeService.chargeEnd(id)
            .map(updatedCharge -> Response.ok(updatedCharge).build())
            .orElse(Response.status(404).build());
    }
}

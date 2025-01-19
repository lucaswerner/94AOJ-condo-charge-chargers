package br.com.condocharge.resources;

import java.util.List;

import br.com.condocharge.dto.PatchStationDTO;
import br.com.condocharge.dto.PostStationDTO;
import br.com.condocharge.dto.StationDTO;
import br.com.condocharge.service.StationService;
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

@Path("/station")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StationResource {

    @Inject
    StationService stationService;

    @GET
    public List<StationDTO> getAll() {
        return stationService.getAll();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id) {
        return stationService.getById(id)
                .map(station -> Response.ok(station).build())
                .orElse(Response.status(404).build());
    }

    @POST
    public Response postStation(@Valid PostStationDTO postedStation, @Context UriInfo uriInfo) {
        final StationDTO savedStation = stationService.saveNewStation(postedStation);
        return Response
                .created(uriInfo
                        .getAbsolutePathBuilder()
                        .path(savedStation.getId())
                        .build())
                .entity(savedStation)
                .build();
    }

    @PATCH
    @Path("{id}")
    public Response patchStation(@PathParam("id") String id, PatchStationDTO patchStationDTO) {
        return stationService.patch(id, patchStationDTO)
                .map(updatedStation -> Response.ok(updatedStation).build())
                .orElse(Response.status(404).build());
    }

}

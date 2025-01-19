package br.com.condocharge.resources;

import java.util.List;

import br.com.condocharge.dto.ChargeDTO;
import br.com.condocharge.dto.CondominiumDTO;
import br.com.condocharge.dto.PatchChargeDTO;
import br.com.condocharge.dto.PatchStationDTO;
import br.com.condocharge.dto.PostChargeDTO;
import br.com.condocharge.dto.PostCondominiumDTO;
import br.com.condocharge.dto.PostStationDTO;
import br.com.condocharge.dto.PutCondominiumDTO;
import br.com.condocharge.dto.StationDTO;
import br.com.condocharge.service.ChargeService;
import br.com.condocharge.service.CondominiumService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/condominiums")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CondominiumResource {

    @Inject
    CondominiumService condominiumService;

    @Inject
    ChargeService chargeService;

    @GET
    public List<CondominiumDTO> getAllCondominiums() {
        return condominiumService.getAll();
    }

    @POST
    public Response registerCondominium(
            PostCondominiumDTO postCondominiumDTO,
            @Context UriInfo uriInfo) {
        final CondominiumDTO registeredCondominium = condominiumService.registerCondominium(postCondominiumDTO);
        return Response.created(uriInfo
                .getAbsolutePathBuilder()
                .path(registeredCondominium.getCnpj())
                .build())
                .entity(registeredCondominium)
                .build();
    }

    @GET
    @Path("{cnpj}")
    public Response getCondominiumByCnpj(@PathParam("cnpj") String cnpj) {
        return condominiumService.findByCnpj(cnpj)
                .map(condominium -> Response.ok(condominium).build())
                .orElse(Response.status(404).build());
    }

    @PUT
    @Path("{cnpj}")
    public Response getCondominiumByCnpj(
            @PathParam("cnpj") String cnpj,
            PutCondominiumDTO putCondominiumDTO) {
        return condominiumService.updateData(cnpj, putCondominiumDTO)
                .map(updatedCondominium -> Response.ok(updatedCondominium).build())
                .orElse(Response.status(404).build());
    }

    @GET
    @Path("{cnpj}/stations")
    public List<StationDTO> getAllStations(@PathParam("cnpj") String cnpj) {
        return condominiumService.findAllStationsByCondominiumCnpj(cnpj);
    }

    @POST
    @Path("{cnpj}/stations")
    public Response registerNewStation(
            @PathParam("cnpj") String cnpj,
            PostStationDTO postStationDTO,
            @Context UriInfo uriInfo) {
        final StationDTO savedStation = condominiumService.registerNewStation(cnpj, postStationDTO);
        return Response.created(uriInfo
                .getAbsolutePathBuilder()
                .path(savedStation.getId())
                .build())
                .entity(savedStation)
                .build();
    }

    @GET
    @Path("{cnpj}/stations/{stationId}")
    public Response getStationById(
            @PathParam("cnpj") String cnpj,
            @PathParam("stationId") String stationId) {
        return condominiumService.getStationById(cnpj, stationId)
                .map(station -> Response.ok(station).build())
                .orElse(Response.status(404).build());
    }

    @PATCH
    @Path("{cnpj}/stations/{stationId}")
    public Response updateStation(
            @PathParam("cnpj") String cnpj,
            @PathParam("stationId") String stationId,
            PatchStationDTO patchStationDTO) {
        return condominiumService.updateStation(cnpj, stationId, patchStationDTO)
                .map(updatedStation -> Response.ok(updatedStation).build())
                .orElse(Response.status(404).build());
    }

    @GET
    @Path("{cnpj}/stations/{stationId}/charges")
    public List<ChargeDTO> getAllStationCharges(
            @PathParam("cnpj") String cnpj,
            @PathParam("stationId") String stationId) {
        return condominiumService.findAllCharges(cnpj, stationId);
    }

    @POST
    @Path("{cnpj}/stations/{stationId}/charges")
    public Response registerNewCharge(
            @PathParam("cnpj") String cnpj,
            @PathParam("stationId") String stationId,
            PostChargeDTO postChargeDTO,
            @Context UriInfo uriInfo) {
        return chargeService.postNewCharge(cnpj, stationId, postChargeDTO)
                .map(newCharge -> {
                    return Response.created(uriInfo
                            .getAbsolutePathBuilder()
                            .path(newCharge.getChargeId().toString())
                            .build())
                            .entity(newCharge)
                            .build();
                })
                .orElse(Response.status(404).build());
    }

    @PATCH
    @Path("{cnpj}/stations/{stationId}/charges/{chargeId}")
    public Response patchChargeInfo(
            @PathParam("cnpj") String cnpj,
            @PathParam("stationId") String stationId,
            @PathParam("chargeId") Long chargeId,
            PatchChargeDTO patchCharge) {
        return chargeService.updateCharging(cnpj, stationId, chargeId, patchCharge)
                .map(charge -> Response.ok(charge).build())
                .orElse(Response.status(404).build());
    }

}

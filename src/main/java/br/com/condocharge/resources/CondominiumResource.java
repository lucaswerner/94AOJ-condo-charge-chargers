package br.com.condocharge.resources;

import java.util.List;

import br.com.condocharge.dto.CondominiumDTO;
import br.com.condocharge.dto.PostCondominiumDTO;
import br.com.condocharge.dto.PutCondominiumDTO;
import br.com.condocharge.service.CondominiumService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/condominium")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CondominiumResource {

    @Inject
    CondominiumService condominiumService;

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
                .map(updatedStation -> Response.ok(updatedStation).build())
                .orElse(Response.status(404).build());
    }

    // @GET
    // @Path("{cnpj}/station")
    // public List<StationDTO> getAllStations(@PathParam("cnpj") String cnpj) {
    // return null;
    // }

    // @POST
    // @Path("{cnpj}/station")
    // public List<StationDTO> registerNewStation(@PathParam("cnpj") String cnpj) {
    // return null;
    // }

    // @GET
    // @Path("{cnpj}/station/{stationId}")
    // public List<StationDTO> getStationById(
    // @PathParam("cnpj") String cnpj,
    // @PathParam("stationId") String stationId) {
    // return null;
    // }

    // @PATCH
    // @Path("{cnpj}/station/{stationId}")
    // public List<StationDTO> updateStation(
    // @PathParam("cnpj") String cnpj,
    // @PathParam("stationId") String stationId,
    // PatchStationDTO patchStationDTO) {
    // return null;
    // }

    // @GET
    // @Path("{cnpj}/station/{stationId}/charge")
    // public List<ChargeDTO> getAllStationCharges(
    // @PathParam("cnpj") String cnpj,
    // @PathParam("stationId") String stationId) {
    // return null;
    // }

    // @POST
    // @Path("{cnpj}/station/{stationId}/charge")
    // public ChargeDTO registerNewCharge(
    // @PathParam("cnpj") String cnpj,
    // @PathParam("stationId") String stationId) {
    // return null;
    // }

    // @GET
    // @Path("{cnpj}/station/{stationId}/charge/{chargeId}")
    // public ChargeDTO getStationChargeById(
    // @PathParam("cnpj") String cnpj,
    // @PathParam("stationId") String stationId) {
    // return null;
    // }

    // @PATCH
    // @Path("{cnpj}/station/{stationId}/charge/{chargeId}")
    // public ChargeDTO patchChargeInfo(
    // @PathParam("cnpj") String cnpj,
    // @PathParam("stationId") String stationId,
    // @PathParam("chargeId") Long chargeId) {
    // return null;
    // }

    // @GET
    // @Path("{cnpj}/station/{stationId}/charge/{chargeId}/status")
    // public ChargeDTO getChargeStatus(
    // @PathParam("cnpj") String cnpj,
    // @PathParam("stationId") String stationId,
    // @PathParam("chargeId") Long chargeId) {
    // return null;
    // }

    // @POST
    // @Path("{cnpj}/station/{stationId}/charge/{chargeId}/status")
    // public ChargeDTO registerChargeStatus(
    // @PathParam("cnpj") String cnpj,
    // @PathParam("stationId") String stationId,
    // @PathParam("chargeId") Long chargeId) {
    // return null;
    // }

}

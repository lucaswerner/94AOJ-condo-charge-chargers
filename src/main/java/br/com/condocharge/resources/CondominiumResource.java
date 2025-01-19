package br.com.condocharge.resources;

import java.util.List;

import br.com.condocharge.dto.CondominiumDTO;
import br.com.condocharge.service.CondominiumService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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

    // @POST
    // public List<CondominiumDTO> registerCondominium(PostCondominiumDTO postCondominiumDTO) {
    //     return null;
    // }

    // @GET
    // @Path("{cnpj}")
    // public List<CondominiumDTO> getCondominiumByCnpj(@PathParam("cnpj") String cnpj) {
    //     return null;
    // }

    // @PATCH
    // @Path("{cnpj}")
    // public List<CondominiumDTO> getCondominiumByCnpj(@PathParam("cnpj") String cnpj,
    //         PatchCondominiumDTO patchCondominiumDTO) {
    //     return null;
    // }

    // @GET
    // @Path("{cnpj}/station")
    // public List<StationDTO> getAllStations(@PathParam("cnpj") String cnpj) {
    //     return null;
    // }

    // @POST
    // @Path("{cnpj}/station")
    // public List<StationDTO> registerNewStation(@PathParam("cnpj") String cnpj) {
    //     return null;
    // }

    // @GET
    // @Path("{cnpj}/station/{stationId}")
    // public List<StationDTO> getStationById(
    //         @PathParam("cnpj") String cnpj,
    //         @PathParam("stationId") String stationId) {
    //     return null;
    // }

    // @PATCH
    // @Path("{cnpj}/station/{stationId}")
    // public List<StationDTO> updateStation(
    //         @PathParam("cnpj") String cnpj,
    //         @PathParam("stationId") String stationId,
    //         PatchStationDTO patchStationDTO) {
    //     return null;
    // }

    // @GET
    // @Path("{cnpj}/station/{stationId}/charge")
    // public List<ChargeDTO> getAllStationCharges(
    //         @PathParam("cnpj") String cnpj,
    //         @PathParam("stationId") String stationId) {
    //     return null;
    // }

    // @POST
    // @Path("{cnpj}/station/{stationId}/charge")
    // public ChargeDTO registerNewCharge(
    //         @PathParam("cnpj") String cnpj,
    //         @PathParam("stationId") String stationId) {
    //     return null;
    // }

    // @GET
    // @Path("{cnpj}/station/{stationId}/charge/{chargeId}")
    // public ChargeDTO getStationChargeById(
    //         @PathParam("cnpj") String cnpj,
    //         @PathParam("stationId") String stationId) {
    //     return null;
    // }

    // @PATCH
    // @Path("{cnpj}/station/{stationId}/charge/{chargeId}")
    // public ChargeDTO patchChargeInfo(
    //         @PathParam("cnpj") String cnpj,
    //         @PathParam("stationId") String stationId,
    //         @PathParam("chargeId") Long chargeId) {
    //     return null;
    // }

    // @GET
    // @Path("{cnpj}/station/{stationId}/charge/{chargeId}/status")
    // public ChargeDTO getChargeStatus(
    //         @PathParam("cnpj") String cnpj,
    //         @PathParam("stationId") String stationId,
    //         @PathParam("chargeId") Long chargeId) {
    //     return null;
    // }

    // @POST
    // @Path("{cnpj}/station/{stationId}/charge/{chargeId}/status")
    // public ChargeDTO registerChargeStatus(
    //         @PathParam("cnpj") String cnpj,
    //         @PathParam("stationId") String stationId,
    //         @PathParam("chargeId") Long chargeId) {
    //     return null;
    // }

}

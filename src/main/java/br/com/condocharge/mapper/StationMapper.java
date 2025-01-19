package br.com.condocharge.mapper;

import br.com.condocharge.dto.PatchStationDTO;
import br.com.condocharge.dto.PostStationDTO;
import br.com.condocharge.dto.StationDTO;
import br.com.condocharge.entities.Station;

public final class StationMapper {

    private StationMapper() {
    }

    public static Station fromDTO(PostStationDTO postStationDTO) {
        final Station entity = new Station();

        entity.setCondominiumId(postStationDTO.getCondominiumId());
        entity.setNumber(postStationDTO.getNumber());

        return entity;
    }

    public static StationDTO fromEntity(Station entity) {
        final StationDTO stationDTO = new StationDTO();

        stationDTO.setId(entity.getId());
        stationDTO.setCondominiumId(entity.getCondominiumId());
        stationDTO.setNumber(entity.getNumber());
        stationDTO.setStatus(entity.getStatus());
        stationDTO.setCreationDt(entity.getCreationDt());
        stationDTO.setLastUpdate(entity.getLastUpdate());
        stationDTO.setCondominiumCnpj(entity.getCondominiumCnpj());

        return stationDTO;
    }

    public static Station fromDTO(PatchStationDTO patchStationDTO) {
        final Station entity = new Station();

        entity.setNumber(patchStationDTO.getNumber());
        entity.setStatus(patchStationDTO.getStatus());

        return entity;
    }

}

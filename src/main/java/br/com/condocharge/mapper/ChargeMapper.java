package br.com.condocharge.mapper;

import br.com.condocharge.dto.ChargeDTO;
import br.com.condocharge.entities.Charge;

public final class ChargeMapper {

    private ChargeMapper() {
    }

    public static ChargeDTO fromEntity(Charge entity) {
        final ChargeDTO chargeDTO = new ChargeDTO();

        chargeDTO.setChargeId(entity.getChargeId());
        chargeDTO.setUserId(entity.getUserId());
        chargeDTO.setStatus(entity.getStatus());
        chargeDTO.setChargeStart(entity.getChargeStart());
        chargeDTO.setChargeEndPreview(entity.getChargeEndPreview());
        chargeDTO.setChargeEnd(entity.getChargeEnd());
        chargeDTO.setEnergyConsumption(entity.getEnergyConsumption());

        chargeDTO.setStation(StationMapper.fromEntity(entity.getStation()));

        return chargeDTO;
    }

}

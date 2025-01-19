package br.com.condocharge.mapper;

import br.com.condocharge.dto.CondominiumDTO;
import br.com.condocharge.entities.Condominium;

public final class CondominiumMapper {

    private CondominiumMapper() {
    }

    public static CondominiumDTO fromEntity(Condominium entity) {
        final CondominiumDTO condominiumDTO = new CondominiumDTO();

        condominiumDTO.setCnpj(entity.getCnpj());
        condominiumDTO.setName(entity.getName());
        condominiumDTO.setAddress(entity.getAddress());

        return condominiumDTO;
    }

}

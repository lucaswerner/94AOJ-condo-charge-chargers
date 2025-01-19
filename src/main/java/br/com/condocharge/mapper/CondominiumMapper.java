package br.com.condocharge.mapper;

import br.com.condocharge.dto.CondominiumDTO;
import br.com.condocharge.dto.PostCondominiumDTO;
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

    public static Condominium fromDTO(PostCondominiumDTO postCondominiumDTO) {
        final Condominium entity = new Condominium();

        entity.setCnpj(postCondominiumDTO.getCnpj());
        entity.setName(postCondominiumDTO.getName());
        entity.setAddress(postCondominiumDTO.getAddress());

        return entity;
    }

}

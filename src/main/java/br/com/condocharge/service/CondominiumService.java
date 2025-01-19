package br.com.condocharge.service;

import java.util.List;
import java.util.Optional;

import br.com.condocharge.dto.CondominiumDTO;
import br.com.condocharge.dto.PostCondominiumDTO;
import br.com.condocharge.dto.PutCondominiumDTO;
import br.com.condocharge.entities.Condominium;
import br.com.condocharge.mapper.CondominiumMapper;
import br.com.condocharge.repository.CondominiumRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CondominiumService {

    @Inject
    CondominiumRepository condominiumRepository;

    public List<CondominiumDTO> getAll() {
        return condominiumRepository.findAll()
                .stream()
                .map(CondominiumMapper::fromEntity)
                .toList();
    }

    @Transactional
    public CondominiumDTO registerCondominium(PostCondominiumDTO postCondominiumDTO) {
        final Condominium condominium = CondominiumMapper.fromDTO(postCondominiumDTO);
        condominiumRepository.persist(condominium);
        return CondominiumMapper.fromEntity(condominium);
    }

    public Optional<CondominiumDTO> findByCnpj(String cnpj) {
        return condominiumRepository.findByIdOptional(cnpj)
                .map(CondominiumMapper::fromEntity);
    }

    @Transactional
    public Optional<CondominiumDTO> updateData(String cnpj, PutCondominiumDTO putCondominiumDTO) {
        final Optional<Condominium> optionalCondominium = condominiumRepository.findByIdOptional(cnpj);
        if (optionalCondominium.isEmpty()) {
            return Optional.empty();
        }

        final Condominium condominiumFromDB = optionalCondominium.get();

        condominiumFromDB.setName(putCondominiumDTO.getName());
        condominiumFromDB.setAddress(putCondominiumDTO.getAddress());

        condominiumRepository.persist(condominiumFromDB);

        return Optional.of(condominiumFromDB)
                .map(CondominiumMapper::fromEntity);
    }

}

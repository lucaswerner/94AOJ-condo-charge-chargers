package br.com.condocharge.service;

import java.util.List;

import br.com.condocharge.dto.CondominiumDTO;
import br.com.condocharge.mapper.CondominiumMapper;
import br.com.condocharge.repository.CondominiumRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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

}

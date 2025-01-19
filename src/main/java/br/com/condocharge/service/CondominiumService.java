package br.com.condocharge.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.com.condocharge.dto.ChargeDTO;
import br.com.condocharge.dto.CondominiumDTO;
import br.com.condocharge.dto.PatchStationDTO;
import br.com.condocharge.dto.PostCondominiumDTO;
import br.com.condocharge.dto.PostStationDTO;
import br.com.condocharge.dto.PutCondominiumDTO;
import br.com.condocharge.dto.StationDTO;
import br.com.condocharge.entities.Charge;
import br.com.condocharge.entities.Condominium;
import br.com.condocharge.entities.Station;
import br.com.condocharge.mapper.ChargeMapper;
import br.com.condocharge.mapper.CondominiumMapper;
import br.com.condocharge.mapper.StationMapper;
import br.com.condocharge.repository.CondominiumRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CondominiumService {

    @Inject
    CondominiumRepository condominiumRepository;

    @Inject
    StationService stationService;

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

    public List<StationDTO> findAllStationsByCondominiumCnpj(String cnpj) {
        return stationService.findAllByCondominiumCnpj(cnpj);
    }

    @Transactional
    public StationDTO registerNewStation(String cnpj, PostStationDTO postStationDTO) {
        final Condominium condominium = condominiumRepository.findById(cnpj);
        return stationService.saveNewStation(condominium, postStationDTO);
    }

    public Optional<StationDTO> getStationById(String cnpj, String stationId) {
        final Optional<Condominium> optionalCondominium = condominiumRepository.findByIdOptional(cnpj);

        if (optionalCondominium.isEmpty()) {
            return Optional.empty();
        }

        return optionalCondominium.get().getStations().stream()
                .filter(station -> station.getId().equals(stationId))
                .findFirst()
                .map(StationMapper::fromEntity);
    }

    @Transactional
    public Optional<StationDTO> updateStation(String cnpj, String stationId, PatchStationDTO patchStationDTO) {
        final Optional<StationDTO> stationById = this.getStationById(cnpj, stationId);

        if (stationById.isEmpty()) {
            return Optional.empty();
        }

        return stationService.updateStation(stationById.get(), patchStationDTO);
    }

    public List<ChargeDTO> findAllCharges(String cnpj, String stationId) {
        final Optional<Condominium> optCondominium = condominiumRepository.findByIdOptional(cnpj);

        if (optCondominium.isPresent()) {
            final Optional<Station> optStation = optCondominium.get()
                    .getStations().stream().filter(station -> station.getId().equals(stationId)).findFirst();

            Optional<Set<Charge>> optCharges = optStation.map(Station::getCharges);

            if (optCharges.isPresent()) {
                return optCharges.get().stream().map(ChargeMapper::fromEntity).toList();
            }
        }

        return Arrays.asList();
    }

}

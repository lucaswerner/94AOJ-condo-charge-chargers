package br.com.condocharge.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.condocharge.dto.PatchStationDTO;
import br.com.condocharge.dto.PostStationDTO;
import br.com.condocharge.dto.StationDTO;
import br.com.condocharge.entities.Condominium;
import br.com.condocharge.entities.Station;
import br.com.condocharge.mapper.StationMapper;
import br.com.condocharge.repository.StationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StationService {

    @Inject
    StationRepository stationRepository;

    public List<StationDTO> getAll() {
        return findAll()
                .stream()
                .map(StationMapper::fromEntity)
                .toList();
    }

    public Optional<StationDTO> getById(String id) {
        return findById(id).map(StationMapper::fromEntity);
    }

    public StationDTO saveNewStation(PostStationDTO postStationDTO) {
        return StationMapper.fromEntity(
                this.persist(StationMapper.fromDTO(postStationDTO)));
    }

    public Optional<StationDTO> patch(String id, PatchStationDTO patchStationDTO) {
        return this.updateStationStatusAndNumber(id, StationMapper.fromDTO(patchStationDTO))
                .map(StationMapper::fromEntity);
    }

    public List<Station> findAll() {
        return stationRepository
                .findAll()
                .list();
    }

    public Optional<Station> findById(String id) {
        return stationRepository.findByIdOptional(id);
    }

    @Transactional
    public Station persist(Station station) {
        stationRepository.persist(station);
        return station;
    }

    @Transactional
    public Optional<Station> updateStationStatusAndNumber(String id, Station station) {
        final Optional<Station> optionalFromDB = stationRepository.findByIdOptional(id);
        if (optionalFromDB.isEmpty()) {
            return optionalFromDB;
        }

        final Station stationFromDB = optionalFromDB.get();
        stationFromDB.setNumber(station.getNumber());
        stationFromDB.setStatus(station.getStatus());
        stationFromDB.setLastUpdate(LocalDateTime.now());

        stationRepository.persist(stationFromDB);

        return Optional.of(stationFromDB);
    }

    public List<StationDTO> findAllByCondominiumCnpj(String condominiumCnpj) {
        return stationRepository.list("condominiumCnpj", condominiumCnpj)
                .stream()
                .map(StationMapper::fromEntity)
                .toList();
    }

    @Transactional
    public StationDTO saveNewStation(Condominium condominium, PostStationDTO postStationDTO) {
        final Station newStation = StationMapper.fromDTO(postStationDTO);
        newStation.setCondominum(condominium);
        stationRepository.persist(newStation);

        return StationMapper.fromEntity(newStation);
    }

    @Transactional
    public Optional<StationDTO> updateStation(StationDTO stationDTO, PatchStationDTO patchStationDTO) {
        final Optional<Station> optStation = stationRepository.findByIdOptional(stationDTO.getId());
        if (optStation.isEmpty()) {
            return Optional.empty();
        }

        final Station station = optStation.get();

        if (!station.getCondominiumCnpj().equals(stationDTO.getCondominiumCnpj())) {
            return Optional.empty();
        }

        station.setNumber(patchStationDTO.getNumber());
        station.setStatus(patchStationDTO.getStatus());

        stationRepository.persist(station);

        return Optional.of(StationMapper.fromEntity(station));
    }

}

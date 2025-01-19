package br.com.condocharge.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.condocharge.dto.ChargeDTO;
import br.com.condocharge.dto.PostChargeDTO;
import br.com.condocharge.entities.Charge;
import br.com.condocharge.entities.Station;
import br.com.condocharge.enums.ChargeStatus;
import br.com.condocharge.mapper.ChargeMapper;
import br.com.condocharge.repository.ChargeRepository;
import br.com.condocharge.repository.StationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ChargeService {

    @Inject
    ChargeRepository chargeRepository;

    @Inject
    StationRepository stationRepository;

    public List<ChargeDTO> getAll() {
        return findAll()
                .stream()
                .map(ChargeMapper::fromEntity)
                .toList();
    }

    public Optional<ChargeDTO> getById(Long id) {
        return findById(id).map(ChargeMapper::fromEntity);
    }

    public List<Charge> findAll() {
        return chargeRepository
                .findAll()
                .list();
    }

    public ChargeDTO registerNewCharge(PostChargeDTO postChargeDTO) {
        return ChargeMapper.fromEntity(
                this.persist(postChargeDTO));
    }

    public Optional<ChargeDTO> chargeEnd(Long id) {
        return registerChargeEnd(id)
                .map(ChargeMapper::fromEntity);
    }

    public Optional<Charge> findById(Long id) {
        return chargeRepository.findByIdOptional(id);
    }

    @Transactional
    public Charge persist(PostChargeDTO postChargeDTO) {
        final Station station = stationRepository.findById(postChargeDTO.getStationId());

        final Charge newCharge = new Charge();

        newCharge.setUserId(postChargeDTO.getUserId());
        newCharge.setStatus(ChargeStatus.CHARGING);
        newCharge.setChargeStart(LocalDateTime.now());
        newCharge.setStation(station);

        chargeRepository.persist(newCharge);
        return newCharge;
    }

    @Transactional
    public Optional<Charge> registerChargeEnd(Long id) {
        final Optional<Charge> optionalCharge = this.findById(id);
        if (optionalCharge.isEmpty()) {
            return optionalCharge;
        }

        final Charge chargeFromDB = optionalCharge.get();
        chargeFromDB.setChargeEnd(LocalDateTime.now());
        chargeFromDB.setStatus(ChargeStatus.READY);

        chargeRepository.persist(chargeFromDB);

        return Optional.of(chargeFromDB);
    }

}

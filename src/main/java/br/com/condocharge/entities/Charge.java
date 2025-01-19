package br.com.condocharge.entities;

import java.time.LocalDateTime;

import br.com.condocharge.enums.ChargeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Charge {
    
    @Id
    @SequenceGenerator(name = "chargeSeq", sequenceName = "charge_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "chargeSeq")
    private Long chargeId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private ChargeStatus status;

    @Column(nullable = false)
    private LocalDateTime chargeStart;

    @Column(nullable = true)
    private LocalDateTime chargeEndPreview;

    @Column(nullable = true)
    private LocalDateTime chargeEnd;

    @Column(nullable = true)
    private Integer energyConsumption;

    @Column(nullable = false, insertable=false, updatable=false)
    private String stationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stationId", nullable = false)
    private Station station;

}

package br.com.condocharge.entities;

import java.time.LocalDateTime;
import java.util.Set;

import br.com.condocharge.enums.StationStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Station extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private Integer condominiumId;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private StationStatus status = StationStatus.ACTIVE;

    @Column(nullable = false)
    private LocalDateTime creationDt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime lastUpdate = LocalDateTime.now();

    @Column(nullable = false, insertable=false, updatable=false)
    private String condominiumCnpj;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "condominiumCnpj", nullable = false)
    private Condominium condominum;

    @OneToMany(mappedBy = "station", fetch = FetchType.LAZY)
    private Set<Charge> charges;

}

package br.com.condocharge.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Condominium {

    @Id
    @Column(unique = true, nullable = false, length = 14)
    private String cnpj;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @OneToMany(mappedBy = "condominum", fetch = FetchType.LAZY)
    private Set<Station> stations;

}

package br.com.condocharge.repository;

import br.com.condocharge.entities.Charge;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ChargeRepository implements PanacheRepositoryBase<Charge, Long> {

}

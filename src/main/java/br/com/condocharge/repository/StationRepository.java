package br.com.condocharge.repository;

import br.com.condocharge.entities.Station;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StationRepository implements PanacheRepositoryBase<Station, String> {

}

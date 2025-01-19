package br.com.condocharge.repository;

import br.com.condocharge.entities.Condominium;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CondominiumRepository implements PanacheRepositoryBase<Condominium, String> {

}

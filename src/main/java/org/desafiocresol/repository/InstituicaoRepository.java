package org.desafiocresol.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;
import org.desafiocresol.entity.InstituicaoEntity;

@RequestScoped
public class InstituicaoRepository implements PanacheRepository<InstituicaoEntity> {
}

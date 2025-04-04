package org.desafiocresol.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.desafiocresol.entity.EventoEntity;

@ApplicationScoped
public class EventoRepository implements PanacheRepository<EventoEntity> {
}

package org.desafiocresol.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.desafiocresol.entity.Evento;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class EventoRepository implements PanacheRepository<Evento> {
    public List<Evento> listarEventosASeremEncerrados() {
        return list("ativo = ?1 AND dataFinal <= ?2",true, LocalDateTime.now());
    }
}

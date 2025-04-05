package org.desafiocresol.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.desafiocresol.dto.EventoDTO;
import org.desafiocresol.entity.Evento;
import org.desafiocresol.record.Page;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class EventoRepository implements PanacheRepository<Evento> {
    public List<Evento> listarEventosASeremEncerrados() {
        return list("ativo = ?1 AND dataFinal <= ?2",true, LocalDateTime.now());
    }

    public Page<EventoDTO> listPaginated(Integer page, Integer pageSize) {
        PanacheQuery<Evento> query = findAll().page(page, pageSize);
        List<EventoDTO> dados = query.list()
                .stream().map(EventoDTO::from)
                .toList();

        return new Page<>(dados, query.count(), query.hasNextPage());
    }
}

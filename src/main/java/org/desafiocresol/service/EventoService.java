package org.desafiocresol.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.desafiocresol.dto.EventoDTO;
import org.desafiocresol.entity.EventoEntity;
import org.desafiocresol.entity.InstituicaoEntity;
import org.desafiocresol.exceptions.AppException;
import org.desafiocresol.repository.EventoRepository;

import java.util.List;

@ApplicationScoped
public class EventoService {

    @Inject
    EventoRepository repository;

    @Inject
    InstituicaoService instituicaoService;

    public EventoEntity create(final EventoDTO dto) {
        final InstituicaoEntity instituicao = instituicaoService.findById(dto.getIdInstituicao());
        final EventoEntity evento = EventoDTO.toEntity(dto, instituicao);

        repository.persist(evento);
        return evento;
    }

    public List<EventoEntity> getAll(Integer page, Integer pageSize) {
        return repository.findAll().page(page, pageSize).list();
    }

    public EventoEntity findById(Integer id){
        return (EventoEntity) repository.findByIdOptional(id.longValue())
                .orElseThrow(() -> new AppException("Evento não encontrado", Response.Status.NOT_FOUND));
    }

    public EventoEntity update(Integer id, EventoDTO dto) {
        final EventoEntity updatedEvento = getUpdatedEntity(id, dto);

        repository.persist(updatedEvento);
        return updatedEvento;
    }

    public void delete(Integer id) {
        final EventoEntity evento = findById(id);
        repository.deleteById(id.longValue());
    }

    private EventoEntity getUpdatedEntity(Integer id, EventoDTO dto) {
        final InstituicaoEntity instituicao = instituicaoService.findById(dto.getIdInstituicao());
        final EventoEntity evento = findById(id);
        return EventoDTO.buildFrom(dto, evento, instituicao);
    }
}

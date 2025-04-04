package org.desafiocresol.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.desafiocresol.dto.EventoDTO;
import org.desafiocresol.entity.EventoEntity;
import org.desafiocresol.entity.InstituicaoEntity;
import org.desafiocresol.repository.EventoRepository;
import org.desafiocresol.repository.InstituicaoRepository;

@ApplicationScoped
public class EventoService {

    @Inject
    EventoRepository repository;

    @Inject
    InstituicaoRepository instituicaoRepository;

    public EventoEntity create(final EventoDTO dto) {
        final InstituicaoEntity instituicao = instituicaoRepository.findById(dto.getIdInstituicao().longValue());
        final EventoEntity evento = EventoDTO.toEntity(dto, instituicao);

        repository.persist(evento);
        return evento;
    }
}

package org.desafiocresol.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.desafiocresol.dto.EventoDTO;
import org.desafiocresol.entity.Evento;
import org.desafiocresol.entity.Instituicao;
import org.desafiocresol.exceptions.AppException;
import org.desafiocresol.record.Page;
import org.desafiocresol.repository.EventoRepository;

import java.util.List;

@ApplicationScoped
public class EventoService {

    @Inject
    EventoRepository repository;

    @Inject
    InstituicaoService instituicaoService;

    public Evento create( EventoDTO dto) {
        Instituicao instituicao = instituicaoService.findById(dto.getIdInstituicao());
        Evento evento = EventoDTO.toEntity(dto, instituicao);
        validaDatas(evento);
        repository.persist(evento);
        return evento;
    }

    public Page<EventoDTO> getAll(Integer page, Integer pageSize) {
        return repository.listPaginated(page, pageSize);
    }

    public Evento findById(Integer id){
        return repository.findByIdOptional(id.longValue())
                .orElseThrow(() -> new AppException("Evento não encontrado", Response.Status.NOT_FOUND));
    }

    public Evento update(Integer id, EventoDTO dto) {
        Evento updatedEvento = getUpdatedEntity(id, dto);
        validaDatas(updatedEvento);

        repository.persist(updatedEvento);
        return updatedEvento;
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id.longValue());
    }

    @Transactional
    public int encerraEventos() {
        List<Evento> eventos = repository.listarEventosASeremEncerrados();
        if (!eventos.isEmpty()) {
            eventos.forEach(evento -> {
                evento.setAtivo(false);
                repository.persist(evento);
            });
            return eventos.size();
        }

        return 0;
    }


    private Evento getUpdatedEntity(Integer id, EventoDTO dto) {
         Instituicao instituicao = instituicaoService.findById(dto.getIdInstituicao());
         Evento evento = findById(id);
        return EventoDTO.buildFrom(dto, evento, instituicao);
    }

    private void validaDatas(Evento evento) {
        if (evento.getDataFinal().isBefore(evento.getDataInicial())) {
            throw new AppException(
                    "A data inicial do evento não pode ser após a data de encerramento",
                    Response.Status.BAD_REQUEST
            );
        }
    }
}

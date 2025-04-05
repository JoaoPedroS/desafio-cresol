package org.desafiocresol.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.desafiocresol.dto.InstituicaoDTO;
import org.desafiocresol.entity.Instituicao;
import org.desafiocresol.exceptions.AppException;
import org.desafiocresol.record.Page;
import org.desafiocresol.repository.InstituicaoRepository;


@RequestScoped
public class InstituicaoService {

    @Inject
    InstituicaoRepository repository;

    public Instituicao create( InstituicaoDTO dto) {
        Instituicao instituicao = InstituicaoDTO.toEntity(dto);
        repository.persist(instituicao);
        return instituicao;
    }

    public Page<InstituicaoDTO> getAll(Integer page, Integer pageSize) {
        return repository.listPaginated(page, pageSize);
    }

    public Instituicao findById(Integer id){
        return repository.findByIdOptional(id.longValue())
                .orElseThrow(() -> new AppException("Instituição não encontrada", Response.Status.NOT_FOUND));
    }

    public Instituicao update(Integer id, InstituicaoDTO dto) {
         Instituicao instituicao = findById(id);
         Instituicao updatedInstituicao = InstituicaoDTO.buildFrom(dto, instituicao);

        repository.persist(updatedInstituicao);
        return updatedInstituicao;
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id.longValue());
    }
}

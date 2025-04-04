package org.desafiocresol.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.desafiocresol.dto.InstituicaoDTO;
import org.desafiocresol.entity.InstituicaoEntity;
import org.desafiocresol.exceptions.NotFoundException;
import org.desafiocresol.repository.InstituicaoRepository;

import java.util.List;

@RequestScoped
public class InstituicaoService {

    @Inject
    InstituicaoRepository repository;

    public InstituicaoEntity create(final InstituicaoDTO dto) {
        InstituicaoEntity instituicao = InstituicaoDTO.toEntity(dto);
        repository.persist(instituicao);
        return instituicao;
    }

    public List<InstituicaoEntity> getAll(Integer page, Integer pageSize) {
        return repository.findAll().page(page, pageSize).list();
    }

    public InstituicaoEntity findById(Integer id){
        return (InstituicaoEntity) repository.findByIdOptional(id.longValue())
                .orElseThrow(NotFoundException::new);
    }

    public InstituicaoEntity update(Integer id, InstituicaoDTO dto) {
        final InstituicaoEntity instituicao = findById(id);
        final InstituicaoEntity updatedInstituicao = InstituicaoDTO.buildFrom(dto, instituicao);

        repository.persist(updatedInstituicao);
        return updatedInstituicao;
    }

    public void delete(Integer id) {
        final InstituicaoEntity instituicao = findById(id);
        repository.deleteById(id.longValue());
    }
}

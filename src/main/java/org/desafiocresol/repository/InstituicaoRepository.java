package org.desafiocresol.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.RequestScoped;
import org.desafiocresol.dto.InstituicaoDTO;
import org.desafiocresol.entity.Instituicao;
import org.desafiocresol.record.Page;

import java.util.List;

@RequestScoped
public class InstituicaoRepository implements PanacheRepository<Instituicao> {

    public Page<InstituicaoDTO> listPaginated(Integer page, Integer pageSize) {
        PanacheQuery<Instituicao> query = findAll(Sort.by("id")).page(page, pageSize);
        List<InstituicaoDTO> dados = query.list()
                .stream().map(InstituicaoDTO::from)
                .toList();

        return new Page<>(dados, query.count(), query.hasNextPage());
    }
}

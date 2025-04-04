package org.desafiocresol.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.desafiocresol.entity.EventoEntity;
import org.desafiocresol.entity.InstituicaoEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    @NotBlank(message = "O nome do evento deve ser informado")
    private String nome;

    @NotBlank(message = "A data inicial do evento deve ser informada")
    private LocalDateTime dataInicial;

    @NotBlank(message = "A data final do evento deve ser informada")
    private LocalDateTime dataFinal;

    @NotBlank(message = "A instituição deve ser informada")
    private Integer idInstituicao;

    @Builder.Default
    private boolean ativo = true;

    public static EventoEntity toEntity(final EventoDTO dto, final InstituicaoEntity instituicao) {
        return EventoEntity.builder()
                .instituicao(instituicao)
                .nome(dto.getNome())
                .dataInicial(dto.getDataInicial())
                .dataFinal(dto.getDataFinal())
                .ativo(true)
                .build();
    }

    public static EventoDTO from(final EventoEntity entity) {
        return EventoDTO.builder()
                .idInstituicao(entity.getInstituicao().getId())
                .nome(entity.getNome())
                .dataInicial(entity.getDataInicial())
                .dataFinal(entity.getDataFinal())
                .ativo(entity.isAtivo())
                .build();
    }

    public static EventoEntity buildFrom(final EventoDTO dto, final EventoEntity entity, final InstituicaoEntity instituicao) {
        entity.setNome(dto.getNome());
        entity.setDataInicial(dto.getDataInicial());
        entity.setDataFinal(dto.getDataFinal());
        entity.setInstituicao(instituicao);
        entity.setAtivo(dto.isAtivo());
        return entity;
    }
}

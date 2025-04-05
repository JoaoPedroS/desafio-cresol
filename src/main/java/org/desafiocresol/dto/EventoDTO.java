package org.desafiocresol.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.desafiocresol.entity.Evento;
import org.desafiocresol.entity.Instituicao;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    private Integer id;

    @NotBlank(message = "O nome do evento deve ser informado")
    private String nome;

    @NotBlank(message = "A data inicial do evento deve ser informada")
    private LocalDateTime dataInicial;

    @NotBlank(message = "A data  do evento deve ser informada")
    private LocalDateTime dataFinal;

    @NotBlank(message = "A instituição deve ser informada")
    private Integer idInstituicao;

    @Builder.Default
    private boolean ativo = true;

    public static Evento toEntity( EventoDTO dto,  Instituicao instituicao) {
        return Evento.builder()
                .instituicao(instituicao)
                .nome(dto.getNome())
                .dataInicial(dto.getDataInicial())
                .dataFinal(dto.getDataFinal())
                .ativo(true)
                .build();
    }

    public static EventoDTO from( Evento entity) {
        return EventoDTO.builder()
                .id(entity.getId())
                .idInstituicao(entity.getInstituicao().getId())
                .nome(entity.getNome())
                .dataInicial(entity.getDataInicial())
                .dataFinal(entity.getDataFinal())
                .ativo(entity.isAtivo())
                .build();
    }

    public static Evento buildFrom( EventoDTO dto,  Evento entity,  Instituicao instituicao) {
        entity.setNome(dto.getNome());
        entity.setDataInicial(dto.getDataInicial());
        entity.setDataFinal(dto.getDataFinal());
        entity.setInstituicao(instituicao);
        entity.setAtivo(dto.isAtivo());
        return entity;
    }
}

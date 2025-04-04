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

    public static EventoEntity toEntity(final EventoDTO dto, InstituicaoEntity instituicao) {
        return EventoEntity.builder()
                .instituicao(instituicao)
                .nome(dto.getNome())
                .dataInicial(dto.getDataInicial())
                .dataFinal(dto.getDataFinal())
                .ativo(dto.isAtivo())
                .build();
    }
}

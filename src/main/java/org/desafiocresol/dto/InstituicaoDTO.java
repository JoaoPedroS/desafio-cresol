package org.desafiocresol.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.desafiocresol.entity.Instituicao;
import org.desafiocresol.enums.TipoInstituicao;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoDTO {
    private Integer id;

    @NotBlank(message = "O nome da instituição deve ser informado")
    private String nome;

    @NotBlank(message = "O tipo da instituição deve ser informado")
    private TipoInstituicao tipo;

    private Collection<EventoDTO> eventos;

    public static Instituicao toEntity( InstituicaoDTO dto) {
        return Instituicao.builder()
                .nome(dto.getNome())
                .tipo(dto.getTipo())
                .build();
    }

    public static InstituicaoDTO from( Instituicao entity) {
        return InstituicaoDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .tipo(entity.getTipo())
                .eventos(getEventosForDto(entity))
                .build();
    }

    public static Instituicao buildFrom( InstituicaoDTO dto,  Instituicao entity) {
         entity.setNome(dto.getNome());
         entity.setTipo(dto.getTipo());
         return entity;
    }

    private static Collection<EventoDTO> getEventosForDto(Instituicao entity) {
         return entity.getEventos().stream()
                .map(EventoDTO::from)
                .toList();
    }
}

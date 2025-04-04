package org.desafiocresol.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.desafiocresol.entity.InstituicaoEntity;
import org.desafiocresol.enums.ETipoInstituicao;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoDTO {
    @NotBlank(message = "O nome da instituição deve ser informado")
    private String nome;

    @NotBlank(message = "O tipo da instituição deve ser informado")
    private ETipoInstituicao tipo;

    public static InstituicaoEntity toEntity(final InstituicaoDTO dto) {
        return InstituicaoEntity.builder()
                .nome(dto.getNome())
                .tipo(dto.getTipo())
                .build();
    }

    public static InstituicaoEntity buildFrom(final InstituicaoDTO dto, final InstituicaoEntity entity) {
         entity.setNome(dto.getNome());
         entity.setTipo(dto.getTipo());
         return entity;
    }
}

package org.desafiocresol.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.desafiocresol.enums.ETipoInstituicao;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "instituicao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class InstituicaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private ETipoInstituicao tipo;

    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Collection<EventoEntity> eventos = new ArrayList<>();
}

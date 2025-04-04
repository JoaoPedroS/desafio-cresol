package org.desafiocresol.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "Eventos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "data_inicial", nullable = false)
    private LocalDateTime dataInicial;

    @Column(name = "data_final", nullable = false)
    private LocalDateTime dataFinal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_instituicao", nullable = false)
    @JsonIgnore
    private InstituicaoEntity instituicao;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;
}

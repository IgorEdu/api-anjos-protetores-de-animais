package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "races")
@Entity(name = "Race")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Race extends AbstractBaseEntity {
    @Column(name = "species_id", insertable = false, updatable = false)
    private UUID specieId;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Specie specie;

    private String name;

    public Race(final Specie specie,
                final String name) {
        this.specie = specie;
        this.name = name;
    }
}

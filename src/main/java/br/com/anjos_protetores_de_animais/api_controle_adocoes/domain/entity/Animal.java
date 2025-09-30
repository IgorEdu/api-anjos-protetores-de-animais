package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "animals")
@Entity(name = "Animal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal extends AbstractBaseEntity {

    private String name;
    private String description;
    private String status; // e.g., "available", "adopted"
    @ManyToOne
    @JoinColumn(name = "id_specie")
    private Specie specie;
    @ManyToOne
    @JoinColumn(name = "id_race")
    private Race race;
}

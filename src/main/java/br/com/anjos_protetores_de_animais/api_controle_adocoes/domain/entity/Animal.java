package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.Column;
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

    private String status; // e.g., "AVAILABLE", "ADOPTED"

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender; // "MALE", "FEMALE", "UNKNOWN"

    @Column(name = "animal_size")
    private String animalSize; // "SMALL", "MEDIUM", "LARGE"

    @Column(name = "photo_url")
    private String photoUrl; // URL da foto no upload

    @ManyToOne
    @JoinColumn(name = "id_specie")
    private Specie specie;

    @ManyToOne
    @JoinColumn(name = "id_race")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "adopted_by_id")
    private User adoptedBy;
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "animals")
@Entity(name = "Animal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String status; // e.g., "available", "adopted"
    private Specie specie;
    private Race race;
}

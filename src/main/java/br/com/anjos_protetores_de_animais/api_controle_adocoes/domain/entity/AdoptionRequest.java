package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "adoption_requests")
@Entity(name = "AdoptionRequest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionRequest extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "adopter_id")
    private User adopter;
}

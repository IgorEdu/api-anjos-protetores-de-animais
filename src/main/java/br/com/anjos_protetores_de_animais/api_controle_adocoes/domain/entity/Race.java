package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "races")
@Entity(name = "Race")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Race extends AbstractBaseEntity {

    private String name;
}

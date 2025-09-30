package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "adopters")
@Entity(name = "Adopter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adopter extends AbstractBaseEntity {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Boolean isDeleted = false;

    public Adopter(final String name,
                   final String email,
                   final String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Adopter(final Long id) {
        super(id);
    }
}

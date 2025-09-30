package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

@Getter
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AbstractBaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5144401939612964877L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    protected Long id;

    public AbstractBaseEntity(@NonNull final Long id) {
        this.id = id;
    }
}

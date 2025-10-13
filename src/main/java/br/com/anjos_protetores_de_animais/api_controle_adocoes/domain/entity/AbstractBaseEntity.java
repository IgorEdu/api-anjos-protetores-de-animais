package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AbstractBaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5144401939612964877L;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    protected UUID id;

    public AbstractBaseEntity(@NonNull final UUID id) {
        this.id = id;
    }
}

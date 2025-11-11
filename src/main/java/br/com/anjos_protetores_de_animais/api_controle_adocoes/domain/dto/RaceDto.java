package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Race;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Specie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RaceDto {

    private final UUID id;

    private final String name;
    private final Specie specie;

    @Builder
    protected RaceDto(final UUID id,
                      final Specie specie,
                      final String name
    ) {
        this.id = id;
        this.specie = specie;
        this.name = name;
    }

    public static RaceDto toDto(final Race entity) {
        return RaceDto.builder()
                .id(entity.getId())
                .specie(entity.getSpecie())
                .name(entity.getName())
                .build();
    }

    public Race toEntity() {
        return new Race(
                this.getSpecie(),
                this.getName()
        );
    }
}

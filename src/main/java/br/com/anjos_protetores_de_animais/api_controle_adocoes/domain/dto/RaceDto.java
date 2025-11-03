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

    @Builder
    protected RaceDto(final UUID id,
                        final String name) {
        this.id = id;
        this.name = name;
    }

    public static RaceDto toDto(final Race entity) {
        return RaceDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .build();
    }
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Animal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalListDto {

    private final UUID id;

    private final String name;
    private final String description;
    private final String status; // e.g., "available", "adopted"
    private final SpecieDto specie;

    @Builder
    protected AnimalListDto(final UUID id,
                            final String name,
                            final String description,
                            final String status,
                            final SpecieDto specie) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.specie = specie;
    }

    public static AnimalListDto toDto(final Animal entity) {
        return AnimalListDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .status(entity.getStatus())
            .specie(SpecieDto.toDto(entity.getSpecie()))
            .build();
    }
}
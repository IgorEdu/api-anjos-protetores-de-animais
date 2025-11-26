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
    private final String status;
    private final Integer age;
    private final String gender;
    private final String animalSize;
    private final String photoUrl;

    private final SpecieDto specie;
    private final RaceDto race;

    @Builder
    protected AnimalListDto(
            final UUID id,
            final String name,
            final String description,
            final String status,
            final Integer age,
            final String gender,
            final String animalSize,
            final String photoUrl,
            final SpecieDto specie,
            final RaceDto race
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.age = age;
        this.gender = gender;
        this.animalSize = animalSize;
        this.photoUrl = photoUrl;
        this.specie = specie;
        this.race = race;
    }

    public static AnimalListDto toDto(final Animal entity) {
        return AnimalListDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .age(entity.getAge())
                .gender(entity.getGender())
                .animalSize(entity.getAnimalSize())
                .photoUrl(entity.getPhotoUrl())
                .specie(SpecieDto.toDto(entity.getSpecie()))
                .race(RaceDto.toDto(entity.getRace()))
                .build();
    }
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.AdoptionRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdoptionRequestDto {

    private final UUID id;

    private final AdopterDto adopter;

    private final AnimalListDto animal;

    @Builder
    protected AdoptionRequestDto(final UUID id,
                                 final AdopterDto adopter,
                                 final AnimalListDto animal) {
        this.id = id;
        this.adopter = adopter;
        this.animal = animal;
    }

    public static AdoptionRequestDto toDto(@NotNull final AdoptionRequest entity) {
        return AdoptionRequestDto.builder()
            .id(entity.getId())
            .adopter(AdopterDto.toDto(entity.getAdopter()))
            .animal(AnimalListDto.toDto(entity.getAnimal()))
            .build();
    }
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalUpdatePayload {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String status;

    @NotBlank
    private String specieId;

    @NotBlank
    private String raceId;
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDto {

    private final String message;

    @Builder
    protected ErrorDto(final String message) {
        this.message = message;
    }

    public static ErrorDto toDto(final Exception e) {
        return ErrorDto.builder()
            .message(e.getMessage())
            .build();
    }
}

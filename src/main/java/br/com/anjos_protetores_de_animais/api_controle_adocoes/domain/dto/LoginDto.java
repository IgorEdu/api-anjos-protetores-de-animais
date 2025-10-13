package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.Instant;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto {

    private final String token;

    private final Instant expiresIn;

    private String tokenType = "Bearer";

    public LoginDto(@NotBlank final String token,
                    @NotBlank Instant expiresIn,
                    String tokenType) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;
    }
}

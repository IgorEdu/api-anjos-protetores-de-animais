package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto {

    private final String token;

    private final int expiresIn;

    public LoginDto(@NonNull final String token,
                    int expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginPayload {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}

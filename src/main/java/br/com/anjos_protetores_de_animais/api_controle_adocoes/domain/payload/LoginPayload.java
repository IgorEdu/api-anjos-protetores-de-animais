package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginPayload {

    @NonNull
    private String email;

    @NonNull
    private String password;
}

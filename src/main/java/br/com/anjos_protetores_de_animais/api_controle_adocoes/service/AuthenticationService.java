package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.LoginDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.LoginPayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.SignUpPayload;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<LoginDto> login(@NonNull final LoginPayload payload);

    ResponseEntity<?> logout();

    ResponseEntity<?> signUp(final SignUpPayload payload);
}

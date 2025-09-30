package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.LoginDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.LoginPayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.SignUpPayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("authenticationController")
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginDto> login(@RequestBody final LoginPayload payload) {
        return this.authenticationService.login(payload);
    }

    @PostMapping("logout")
    public void logout() {

    }

    @PostMapping("signUp")
    public ResponseEntity<?> signUp(@RequestBody final SignUpPayload payload) {
        return this.authenticationService.signUp(payload);
    }
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pvt;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.LoginDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.LoginPayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.SignUpPayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("authenticationController")
@RequestMapping(value = "/api/pvt/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginDto> login(@RequestBody @Valid final LoginPayload payload) {
        return this.authenticationService.login(payload);
    }

    @GetMapping("logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("signUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid final SignUpPayload payload) {
        return this.authenticationService.signUp(payload);
    }
}

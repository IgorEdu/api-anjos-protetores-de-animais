package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.LoginDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Adopter;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.LoginPayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.SignUpPayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.AdopterRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AuthenticationService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("authenticationService")
@Transactional(readOnly = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final List<Adopter> users = List.of(
            new Adopter("Murilo", "murilo@gmail.com", "pass123", "phone", "address", false)
    );

    private final AdopterRepository repository;

    public AuthenticationServiceImpl(final AdopterRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<LoginDto> login(@NonNull final LoginPayload payload) {
        final String email = payload.getEmail();
        final String password = payload.getPassword();

        final Optional<Adopter> possibleUser = this.repository.findOneByEmail(email);
        if (possibleUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        final Adopter user = possibleUser.get();
        if (user.getPassword().equals(password)) {
            final LoginDto response = new LoginDto(
                    "token",
                    6969
            );
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest()
                .build();
    }

    @Override
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> signUp(final SignUpPayload payload) {
        final String email = payload.getEmail();
        final String name = payload.getName();
        final String password = payload.getPassword();

        final Optional<Adopter> possibleUser = this.repository.findOneByEmail(email);
        if (possibleUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        final Adopter user = new Adopter(
                name, email, password
        );

        this.repository.save(user);

        return ResponseEntity.ok(null);
    }
}

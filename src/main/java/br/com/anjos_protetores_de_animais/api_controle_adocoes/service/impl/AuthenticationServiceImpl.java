package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.config.security.TokenService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.LoginDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Adopter;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.LoginPayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.SignUpPayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.AdopterRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AuthenticationService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("authenticationService")
@Transactional(readOnly = true)
public class AuthenticationServiceImpl implements AuthenticationService {


    private final AdopterRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(final AdopterRepository repository,
                                     final TokenService tokenService,
                                     final PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<LoginDto> login(@NotBlank final LoginPayload payload) {
        final String email = payload.getEmail();
        final String rawPassword = payload.getPassword();

//        final Optional<Adopter> possibleUser = users.stream()
//                .filter(user -> user.getEmail().equals(email))
//                .findFirst();

        //  cenário real usando o repositório
         final Optional<Adopter> possibleUser = this.repository.findOneByEmail(email);

        if (possibleUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        final Adopter user = possibleUser.get();

        // Validar a senha com o PasswordEncoder
         if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
//        if (!user.getPassword().equals(rawPassword)) {
            return ResponseEntity.badRequest().build();
        }

        var token = tokenService.generateToken(user);
        var tokenExpiration = tokenService.generationExpirationDate();

        final LoginDto response = new LoginDto(
                token,
                tokenExpiration,
                "Bearer"
        );
        return ResponseEntity.ok(response);

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
        final String rawPassword = payload.getPassword();

        final Optional<Adopter> possibleUser = this.repository.findOneByEmail(email);
        if (possibleUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        final String password = passwordEncoder.encode(rawPassword);

        final Adopter user = new Adopter(
                name, email, password
        );
        
        // Garantimos que é USER (embora o construtor já faça isso)
        user.setRole(Role.USER); 

        this.repository.save(user);

        return ResponseEntity.ok(null);
    }
    
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.UserDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.User;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.UserUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.BaseException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController("userController")
@RequestMapping(value = "/api/users")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("profile")
    public ResponseEntity<UserDto> getCurrentAdopter() {
        try {
            final User user = this.userService.getCurrentUserProfile();

            final UserDto response = UserDto.toDto(user);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable final UUID id,
                                          @Valid @RequestBody final UserUpdatePayload payload) {
        try {
            final User user = this.userService.update(id, payload);
            final UserDto response = UserDto.toDto(user);
            return ResponseEntity.ok(response);
        } catch (BaseException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable final UUID id) {
        try {
            this.userService.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (BaseException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
}

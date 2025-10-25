package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.UserDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Adopter;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AdopterUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.BaseException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AdopterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController("adopterController")
@RequestMapping(value = "/api/adopters")
public class AdopterController extends BaseController {

    private final AdopterService adopterService;

    public AdopterController(final AdopterService adopterService) {
        this.adopterService = adopterService;
    }

    @GetMapping("profile")
    public ResponseEntity<UserDto> getCurrentAdopter() {
        try {
            final Adopter user = this.adopterService.getCurrentUserProfile();

            final UserDto response = UserDto.toDto(user);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable final UUID id,
                                          @Valid @RequestBody final AdopterUpdatePayload payload) {
        try {
            final Adopter user = this.adopterService.update(id, payload);
            final UserDto response = UserDto.toDto(user);
            return ResponseEntity.ok(response);
        } catch (BaseException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable final UUID id) {
        try {
            this.adopterService.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (BaseException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pub;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pvt.BaseController;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.User;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AnimalService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("publicAnimalController")
@RequestMapping(value = "/api/pub/animals")
public class AnimalController extends BaseController {

    private final AnimalService animalService;
    private final UserService userService;

    public AnimalController(final AnimalService animalService, final UserService userService) {
        this.animalService = animalService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalListDto>> findAllAnimals() {
        final List<AnimalListDto> animals = this.animalService.findAllAnimals();

        return ResponseEntity.ok(animals);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> requestAdoption(@PathVariable final UUID id) {
        try {
            final User user = userService.getCurrentUserProfile();
            final UUID adopterId = user.getId();

            return this.animalService.requestAdoption(id, adopterId);
        } catch (UnauthorizedException e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
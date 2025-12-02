package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pub;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pvt.BaseController;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalDetailsDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.User;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AdoptionRequestService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AnimalService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("publicAnimalController")
@RequestMapping(value = "/api/pub/animals")
public class AnimalController extends BaseController {

    private final AnimalService animalService;
    private final UserService userService;
    private final AdoptionRequestService adoptionRequestService;

    public AnimalController(final AnimalService animalService, final UserService userService, final AdoptionRequestService adoptionRequestService) {
        this.animalService = animalService;
        this.userService = userService;
        this.adoptionRequestService = adoptionRequestService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalListDto>> findAllUnadoptedAnimals() {
        final List<AnimalListDto> animals = this.animalService.findAllUnadoptedAnimals();

        return ResponseEntity.ok(animals);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimalDetailsDto> getById(@PathVariable final UUID id) {
        return this.animalService.getAnimalById(id);
    }

    @PostMapping("/request/{id}")
    public ResponseEntity<?> requestAdoption(@PathVariable final UUID id) {
        try {
            final User user = userService.getCurrentUserProfile();
            final UUID adopterId = user.getId();

            return this.adoptionRequestService.requestAdoption(id, adopterId);
        } catch (UnauthorizedException e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/request/check/{animalId}")
    public ResponseEntity<Boolean> checkAdoptionStatus(@PathVariable UUID animalId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Se não estiver logado, não tem pedido
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.ok(false);
        }

        User user = (User) authentication.getPrincipal();
        boolean exists = adoptionRequestService.isAdoptionRequested(animalId, user.getId());
        
        return ResponseEntity.ok(exists);
    }
}
package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pvt;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AnimalUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.AnimalNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AdoptionRequestService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController("privateAnimalController")
@RequestMapping(value = "/api/pvt/animals")
public class AnimalController extends BaseController {

    private final AnimalService animalService;
    private final AdoptionRequestService adoptionRequestService;

    public AnimalController(final AnimalService animalService, final AdoptionRequestService adoptionRequestService) {
        this.animalService = animalService;
        this.adoptionRequestService = adoptionRequestService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalListDto>> findAllUnadoptedAnimals() {
        final List<AnimalListDto> animals = this.animalService.findAllAnimals();

        return ResponseEntity.ok(animals);
    }

    @GetMapping("/allAnimals")
    public ResponseEntity<List<AnimalListDto>> findAllAnimals() {
        final List<AnimalListDto> animals = this.animalService.findAllAnimals();

        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}/adoptionRequests")
    public ResponseEntity<?> findAllAdoptionRequests(@PathVariable final UUID id) {
        return this.adoptionRequestService.findAllAdoptionRequestsByAnimal(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody final AnimalUpdatePayload payload) {
        return this.animalService.createAnimal(payload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final UUID id, @Valid @RequestBody final AnimalUpdatePayload payload) throws AnimalNotFoundException {
        return this.animalService.updateAnimal(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final UUID id) {
        try {
            this.animalService.deleteAnimal(id);
            return ResponseEntity.ok().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(e.getStatus())
                .body(null);
        } catch (Exception e) {
            // Retorna 404 se não achar ou 400 se der outro erro
            return ResponseEntity.notFound().build();
        }
    }
}
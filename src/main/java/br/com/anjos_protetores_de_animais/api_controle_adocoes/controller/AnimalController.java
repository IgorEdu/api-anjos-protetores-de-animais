package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AnimalUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("animalController")
@RequestMapping(value = "/api/animals")
public class AnimalController extends BaseController {

    private final AnimalService animalService;

    public AnimalController(final AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalListDto>> findAllAnimals() {
        final List<AnimalListDto> animals = this.animalService.findAllAnimals();

        return ResponseEntity.ok(animals);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody final AnimalUpdatePayload payload) {
        return this.animalService.createAnimal(payload);
    }
}
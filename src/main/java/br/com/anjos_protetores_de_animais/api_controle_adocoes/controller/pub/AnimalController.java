package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pub;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pvt.BaseController;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController("publicAnimalController")
@RequestMapping(value = "/api/pub/animals")
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
}
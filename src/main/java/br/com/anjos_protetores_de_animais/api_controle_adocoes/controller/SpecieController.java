package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.ErrorDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.SpecieDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Specie;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.NamePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.SpecieNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.SpecieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("specieController")
@RequestMapping(value = "/api/species")
public class SpecieController extends BaseController {

    private final SpecieService specieService;

    public SpecieController(final SpecieService specieService) {
        this.specieService = specieService;
    }

    @GetMapping
    public ResponseEntity<List<SpecieDto>> findAll() {
        final List<SpecieDto> animals = this.specieService.findAllSpecies();

        return ResponseEntity.ok(animals);
    }

    @PostMapping
    public ResponseEntity<SpecieDto> create(@Valid @RequestBody final NamePayload payload) {
        final SpecieDto specie = this.specieService.create(payload);

        return ResponseEntity.ok(specie);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable final UUID id) {
        try {
            this.specieService.deleteById(id);

            return ResponseEntity.ok(null);
        } catch (SpecieNotFoundException e) {
            return ResponseEntity.status(e.getStatus())
                .body(ErrorDto.toDto(e));
        }
    }
}

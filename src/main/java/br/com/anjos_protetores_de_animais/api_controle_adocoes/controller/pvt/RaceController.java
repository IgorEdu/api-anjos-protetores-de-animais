package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pvt;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.ErrorDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.RaceDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.NamePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.RaceNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.SpecieNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.RaceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("raceController")
@RequestMapping(value = "/api/pvt/species/{specieId}/races")
public class RaceController extends BaseController {

    private final RaceService raceService;

    public RaceController(final RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public ResponseEntity<List<RaceDto>> findAll(@PathVariable final UUID specieId) {
        final List<RaceDto> races = this.raceService.findAllBySpecie(specieId);

        return ResponseEntity.ok(races);
    }

    @PostMapping
    public ResponseEntity<?> create(@PathVariable final UUID specieId,
                                          @Valid @RequestBody final NamePayload payload) {
        try {
            final RaceDto race = this.raceService.create(payload, specieId);

            return ResponseEntity.ok(race);
        } catch (SpecieNotFoundException e) {
            return ResponseEntity.status(e.getStatus())
                .body(ErrorDto.toDto(e));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable final UUID id) {
        try {
            this.raceService.deleteById(id);

            return ResponseEntity.ok(null);
        } catch (RaceNotFoundException e) {
            return ResponseEntity.status(e.getStatus())
                .body(ErrorDto.toDto(e));
        }
    }
}

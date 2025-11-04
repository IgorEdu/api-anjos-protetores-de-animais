package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.RaceDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Race;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.RaceUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.RaceRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.RaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("raceService")
@Transactional(readOnly = true)
public class RaceServiceImpl implements RaceService {

    private final RaceRepository repository;

    public RaceServiceImpl(final RaceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RaceDto> findAllRaces() {
        final List<Race> races = this.repository.findAll();

        return races.stream()
            .map(RaceDto::toDto)
            .toList();
    }

    @Override
    public RaceDto findRaceById(UUID id) {
        final Race race = this.repository.findById(id).orElseThrow();

        return RaceDto.toDto(race);
    }

    @Override
    public ResponseEntity<?> createRace(RaceUpdatePayload payload) {
        final String name = payload.getName();

        final Race race = new Race(
                name
        );

        this.repository.save(race);

        return ResponseEntity.ok(null);
    }
}

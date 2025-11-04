package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.RaceDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.SpecieDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Animal;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Race;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Specie;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AnimalUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.AnimalRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AnimalService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.RaceService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.SpecieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("animalService")
@Transactional(readOnly = true)
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository repository;

    private final SpecieService specieService;
    private final RaceService raceService;

    public AnimalServiceImpl(final AnimalRepository repository, final SpecieService specieService, final RaceService raceService) {
        this.repository = repository;
        this.specieService = specieService;
        this.raceService = raceService;
    }

    @Override
    public List<AnimalListDto> findAllAnimals() {
        final List<Animal> animals = this.repository.findAll();

        return animals.stream()
            .map(AnimalListDto::toDto)
            .toList();
    }

    @Override
    public ResponseEntity<?> createAnimal(AnimalUpdatePayload payload) {
        final String name = payload.getName();
        final String description = payload.getDescription();
        final String status = payload.getStatus();
        final String specieId = payload.getSpecieId();
        final String raceId = payload.getRaceId();

        final SpecieDto specieDto = this.specieService.findSpecieById(UUID.fromString(specieId));
        final Specie specie = specieDto.toEntity();

        final RaceDto raceDto = this.raceService.findRaceById(UUID.fromString(raceId));
        final Race race = raceDto.toEntity();

        final Animal animal = new Animal(
                name,
                description,
                status,
                specie,
                race
        );

        this.repository.save(animal);

        return ResponseEntity.ok(null);
    }
}

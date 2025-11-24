package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AdoptionRequestDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalDetailsDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.*;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AnimalUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.AnimalNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UserNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.*;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AnimalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("animalService")
@Transactional(readOnly = true)
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final SpecieRepository specieRepository;
    private final RaceRepository raceRepository;

    // Atualizar construtor
    public AnimalServiceImpl(final AnimalRepository animalRepository,
                             final SpecieRepository specieRepository,
                             final RaceRepository raceRepository
                             ) {
        this.animalRepository = animalRepository;
        this.specieRepository = specieRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public List<AnimalListDto> findAllAnimals() {
        final List<Animal> animals = this.animalRepository.findAll();
        return animals.stream()
                .map(AnimalListDto::toDto)
                .toList();
    }

    @Override
    public List<AnimalListDto> findAllUnadoptedAnimals() {
        final List<Animal> animals = this.animalRepository.findByAdoptedByIdIsNull();
        return animals.stream()
                .map(AnimalListDto::toDto)
                .toList();
    }

    @Override
    public ResponseEntity<AnimalDetailsDto> getAnimalById(final UUID id) {
        try {
            final Animal animal = animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);

            return ResponseEntity.ok(
                AnimalDetailsDto.toDto(animal)
            );
        } catch (AnimalNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> createAnimal(AnimalUpdatePayload payload) {
        final Specie specie = this.specieRepository.getReferenceById(UUID.fromString(payload.getSpecieId()));
        final Race race = this.raceRepository.getReferenceById(UUID.fromString(payload.getRaceId()));

        final Animal animal = new Animal();

        animal.setName(payload.getName());
        animal.setDescription(payload.getDescription());
        animal.setStatus(payload.getStatus());
        animal.setSpecie(specie);
        animal.setRace(race);

        this.animalRepository.save(animal);

        return ResponseEntity.ok(null);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateAnimal(UUID id, AnimalUpdatePayload payload) {
        try {
            final Specie specie = this.specieRepository.getReferenceById(UUID.fromString(payload.getSpecieId()));
            final Race race = this.raceRepository.getReferenceById(UUID.fromString(payload.getRaceId()));

            final Animal animal = animalRepository.findById(id).orElseThrow(AnimalNotFoundException::new);

            animal.setName(payload.getName());
            animal.setDescription(payload.getDescription());
            animal.setStatus(payload.getStatus());
            animal.setSpecie(specie);
            animal.setRace(race);

            this.animalRepository.save(animal);

            return ResponseEntity.ok(null);
        } catch (AnimalNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @Transactional
    public void deleteAnimal(UUID id) {
        if (!this.animalRepository.existsById(id)) {
            throw new EntityNotFoundException("Animal not found with id: " + id);
        }
        this.animalRepository.deleteById(id);
    }
}
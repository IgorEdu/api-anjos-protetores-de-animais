package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AdoptionRequestDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.AdoptionRequest;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Animal;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Race;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Specie;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AnimalUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.AnimalNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.AdoptionRequestRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.AnimalRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.RaceRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.SpecieRepository;
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
    private final AdoptionRequestRepository adoptionRequestRepository;

    // Atualizar construtor
    public AnimalServiceImpl(final AnimalRepository animalRepository,
                             final SpecieRepository specieRepository,
                             final RaceRepository raceRepository,
                             final AdoptionRequestRepository adoptionRequestRepository) {
        this.animalRepository = animalRepository;
        this.specieRepository = specieRepository;
        this.raceRepository = raceRepository;
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    @Override
    public List<AnimalListDto> findAllAnimals() {
        final List<Animal> animals = this.animalRepository.findAll();
        return animals.stream()
                .map(AnimalListDto::toDto)
                .toList();
    }

    @Override
    public ResponseEntity<List<AdoptionRequestDto>> findAllAdoptionRequestsByAnimal(@NotNull final UUID id) {
        try {
            this.animalRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

            final List<AdoptionRequest> adoptionRequests = this.adoptionRequestRepository.findAllWithAllDependenciesByAnimalId(
                id
            );

            final List<AdoptionRequestDto> requestsDto = adoptionRequests.stream()
                .map(AdoptionRequestDto::toDto)
                .toList();

            return ResponseEntity.ok(requestsDto);
        } catch (EntityNotFoundException e) {
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
    public ResponseEntity<?> updateAnimal(UUID id, AnimalUpdatePayload payload) throws AnimalNotFoundException {
        final Specie specie = this.specieRepository.getReferenceById(UUID.fromString(payload.getSpecieId()));
        final Race race = this.raceRepository.getReferenceById(UUID.fromString(payload.getRaceId()));

        final Optional<Animal> persistedAnimal = animalRepository.findById(id);

        if (persistedAnimal.isEmpty())
            throw new AnimalNotFoundException();

        final Animal animal = persistedAnimal.get();

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
    public void deleteAnimal(UUID id) {
        if (!this.animalRepository.existsById(id)) {
            throw new EntityNotFoundException("Animal not found with id: " + id);
        }
        this.animalRepository.deleteById(id);
    }
}
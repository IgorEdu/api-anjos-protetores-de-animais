package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AdoptionRequestDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.*;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.AnimalAlreadyAdoptedException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UserNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.*;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AdoptionRequestService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("adoptionRequestService")
@Transactional
public class AdoptionRequestRequestServiceImpl implements AdoptionRequestService {

    private final AnimalRepository animalRepository;
    private final AdoptionRequestRepository adoptionRequestRepository;
    private final UserRepository userRepository;


    public AdoptionRequestRequestServiceImpl(final AnimalRepository animalRepository,
                                             final AdoptionRequestRepository adoptionRequestRepository,
                                             final UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AdoptionRequestDto> findAllRequestAdoption() {
        final List<AdoptionRequest> adoptionRequests = this.adoptionRequestRepository.findAll();
        return adoptionRequests.stream()
                .map(AdoptionRequestDto::toDto)
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
    public ResponseEntity<?> requestAdoption(@NotNull final UUID animalId, @NotNull final UUID adopterId) {
        try {
            final Animal animal = this.animalRepository.findById(animalId)
                    .orElseThrow(EntityNotFoundException::new);

            final User adopter = this.userRepository.findById(adopterId)
                    .orElseThrow(UserNotFoundException::new);

            if(animal.getAdoptedBy() != null){
                throw new AnimalAlreadyAdoptedException();
            }

            AdoptionRequest adoptionRequest = new AdoptionRequest();

            adoptionRequest.setAdopter(adopter);
            adoptionRequest.setAnimal(animal);

            return ResponseEntity.ok(this.adoptionRequestRepository.save(adoptionRequest));
        } catch (EntityNotFoundException | UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (AnimalAlreadyAdoptedException e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @Override
    public ResponseEntity<?> acceptRequestAdoption(@NotNull final UUID requestAdoptionId) {
        try{
            final AdoptionRequest adoptionRequest = this.adoptionRequestRepository.findById(requestAdoptionId)
                    .orElseThrow(EntityNotFoundException::new);

            final User adopter = adoptionRequest.getAdopter();

            final Animal animal = adoptionRequest.getAnimal();
            animal.setAdoptedBy(adopter);
            animal.setStatus("ADOPTED");
            this.animalRepository.save(animal);

            return ResponseEntity.ok(animal);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
        public ResponseEntity<?> revokeRequestAdoption(@NotNull final UUID requestAdoptionId) {
            try {
                final AdoptionRequest adoptionRequest = this.adoptionRequestRepository.findById(requestAdoptionId)
                        .orElseThrow(EntityNotFoundException::new);

                final Animal animal = adoptionRequest.getAnimal();

                // Só reverte se o animal estiver adotado
                if ("ADOPTED".equals(animal.getStatus())) {
                    animal.setAdoptedBy(null);
                    animal.setStatus("AVAILABLE");
                    this.animalRepository.save(animal);
                }

                return ResponseEntity.ok(animal);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }

    @Override
    public ResponseEntity<?> deleteRequestAdoption(@NotNull final UUID requestAdoptionId) {
        try {
            final AdoptionRequest adoptionRequest = this.adoptionRequestRepository.findById(requestAdoptionId)
                    .orElseThrow(EntityNotFoundException::new);
            
            // Remove o pedido do banco
            this.adoptionRequestRepository.delete(adoptionRequest);

            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
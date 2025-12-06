package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AdoptionRequestDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AdoptionRequestService {
    List<AdoptionRequestDto> findAllRequestAdoption();

    ResponseEntity<List<AdoptionRequestDto>> findAllAdoptionRequestsByAnimal(@NotNull UUID id);

    ResponseEntity<?> requestAdoption(final UUID animalId, final UUID adopterId);
    ResponseEntity<?> acceptRequestAdoption(final UUID requestAdoptionId);
    ResponseEntity<?> revokeRequestAdoption(final UUID requestAdoptionId);
    ResponseEntity<?> deleteRequestAdoption(final UUID requestAdoptionId);
    boolean isAdoptionRequested(UUID animalId, UUID adopterId);
    List<AdoptionRequestDto> findAllAdoptionRequestsByAdopter(UUID adopterId);
}

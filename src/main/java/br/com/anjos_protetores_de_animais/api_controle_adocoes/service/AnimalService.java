package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AdoptionRequestDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AnimalUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.AnimalNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AnimalService {

    List<AnimalListDto> findAllAnimals();
    ResponseEntity<?> createAnimal(final AnimalUpdatePayload payload);
    ResponseEntity<?> updateAnimal(final UUID id, final AnimalUpdatePayload payload);
    void deleteAnimal(UUID id);
}

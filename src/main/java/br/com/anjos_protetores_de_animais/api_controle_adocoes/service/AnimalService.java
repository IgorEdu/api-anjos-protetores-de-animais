package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AnimalUpdatePayload;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AnimalService {

    List<AnimalListDto> findAllAnimals();
    ResponseEntity<?> createAnimal(final AnimalUpdatePayload payload);
    void deleteAnimal(UUID id);

}

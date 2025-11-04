package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.RaceDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.RaceUpdatePayload;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface RaceService {

    List<RaceDto> findAllRaces();
    RaceDto findRaceById(final UUID id);
    ResponseEntity<?> createRace(final RaceUpdatePayload payload);

}

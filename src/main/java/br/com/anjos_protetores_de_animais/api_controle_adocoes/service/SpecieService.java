package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.SpecieDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.SpecieUpdatePayload;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface SpecieService {

    List<SpecieDto> findAllSpecies();
    SpecieDto findSpecieById(final UUID id);
    ResponseEntity<?> createSpecie(final SpecieUpdatePayload payload);

}

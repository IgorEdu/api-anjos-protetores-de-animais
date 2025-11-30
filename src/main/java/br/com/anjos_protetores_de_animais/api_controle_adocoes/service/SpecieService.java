package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.SpecieDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.NamePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.SpecieNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface SpecieService {

    List<SpecieDto> findAllSpecies();

    SpecieDto findSpecieById(final UUID id);

    SpecieDto create(@NotNull @NotNull final NamePayload payload) throws UnauthorizedException;

    void deleteById(@NotNull final UUID id) throws SpecieNotFoundException, UnauthorizedException;

}

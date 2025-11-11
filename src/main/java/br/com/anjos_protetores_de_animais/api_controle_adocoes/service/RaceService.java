package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.RaceDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.NamePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.RaceNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.SpecieNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface RaceService {

    List<RaceDto> findAllBySpecie(@NonNull @NotNull final UUID specieId);

    RaceDto findRaceById(final UUID id);

    RaceDto create(@NonNull @NotNull final NamePayload payload,
                   @NonNull @NotNull final UUID specieId) throws SpecieNotFoundException;

    void deleteById(@NonNull @NotNull final UUID id) throws RaceNotFoundException;

}

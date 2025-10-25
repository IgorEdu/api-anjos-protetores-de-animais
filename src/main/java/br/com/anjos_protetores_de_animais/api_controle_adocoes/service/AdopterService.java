package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Adopter;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AdopterUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.AdopterNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.ForbiddenException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.UUID;

public interface AdopterService {

    Adopter getCurrentUserProfile() throws UnauthorizedException;

    Adopter update(@NonNull @NotNull final UUID id,
                   @NonNull @NotNull final AdopterUpdatePayload payload) throws AdopterNotFoundException, ForbiddenException, UnauthorizedException;

    void deleteById(@NonNull @NotNull final UUID id) throws AdopterNotFoundException, ForbiddenException, UnauthorizedException;
}

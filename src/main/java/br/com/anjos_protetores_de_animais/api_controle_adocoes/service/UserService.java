package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.User;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.UserUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UserNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.ForbiddenException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.util.UUID;

public interface UserService {

    User getCurrentUserProfile() throws UnauthorizedException;

    User update(@NonNull @NotNull final UUID id,
                @NonNull @NotNull final UserUpdatePayload payload) throws UserNotFoundException, ForbiddenException, UnauthorizedException;

    void deleteById(@NonNull @NotNull final UUID id) throws UserNotFoundException, ForbiddenException, UnauthorizedException;
}

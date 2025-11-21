package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.User;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.UserUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UserNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.ForbiddenException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.UserRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.UserService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.util.SecurityUtils;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getCurrentUserProfile() throws UnauthorizedException {
        return SecurityUtils.getCurrentUser();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(@NonNull @NotNull final UUID id,
                       @NonNull @NotNull final UserUpdatePayload payload)
        throws UserNotFoundException, ForbiddenException, UnauthorizedException {
        final User currentUser = SecurityUtils.getCurrentUser();

        final User user = this.repository.findOneByIdAndIsDeletedFalse(id)
            .orElseThrow(UserNotFoundException::new);

        if (!currentUser.getId().equals(user.getId())) {
            throw new ForbiddenException();
        }

        final String name = payload.getName();
        final String phone = payload.getPhone();
        final String address = payload.getAddress();

        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);

        return this.repository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(@NonNull @NotNull final UUID id) throws UserNotFoundException, ForbiddenException, UnauthorizedException {
        final User currentUser = SecurityUtils.getCurrentUser();

        final User user = this.repository.findOneByIdAndIsDeletedFalse(id)
            .orElseThrow(UserNotFoundException::new);

        if (!currentUser.getId().equals(user.getId())) {
            throw new ForbiddenException();
        }

        user.setIsDeleted(Boolean.TRUE);
        this.repository.save(user);
    }
}

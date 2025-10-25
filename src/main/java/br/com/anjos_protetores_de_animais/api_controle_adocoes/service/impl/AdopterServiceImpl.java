package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Adopter;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.AdopterUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.AdopterNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.ForbiddenException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.AdopterRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AdopterService;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.util.SecurityUtils;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service("adopterService")
@Transactional(readOnly = true)
public class AdopterServiceImpl implements AdopterService {

    private final AdopterRepository repository;

    public AdopterServiceImpl(final AdopterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Adopter getCurrentUserProfile() throws UnauthorizedException {
        return SecurityUtils.getCurrentUser();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Adopter update(@NonNull @NotNull final UUID id,
                          @NonNull @NotNull final AdopterUpdatePayload payload)
        throws AdopterNotFoundException, ForbiddenException, UnauthorizedException {
        final Adopter currentUser = SecurityUtils.getCurrentUser();

        final Adopter user = this.repository.findOneByIdAndIsDeletedFalse(id)
            .orElseThrow(AdopterNotFoundException::new);

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
    public void deleteById(@NonNull @NotNull final UUID id) throws AdopterNotFoundException, ForbiddenException, UnauthorizedException {
        final Adopter currentUser = SecurityUtils.getCurrentUser();

        final Adopter user = this.repository.findOneByIdAndIsDeletedFalse(id)
            .orElseThrow(AdopterNotFoundException::new);

        if (!currentUser.getId().equals(user.getId())) {
            throw new ForbiddenException();
        }

        user.setIsDeleted(Boolean.TRUE);
        this.repository.save(user);
    }
}

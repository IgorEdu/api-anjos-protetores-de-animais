package br.com.anjos_protetores_de_animais.api_controle_adocoes.util;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.User;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public final class SecurityUtils {

    public static User getCurrentUser() throws UnauthorizedException {
        return getContext()
            .map(authentication -> {
                final Object principal = authentication.getPrincipal();

                if (principal instanceof UserDetails) {
                    return ((User) principal);
                }

                return null;
            })
            .orElseThrow(UnauthorizedException::new);
    }

    private static Optional<Authentication> getContext() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }
}

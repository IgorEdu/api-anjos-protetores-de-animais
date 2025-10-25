package br.com.anjos_protetores_de_animais.api_controle_adocoes.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
        super("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}

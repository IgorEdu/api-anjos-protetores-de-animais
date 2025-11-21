package br.com.anjos_protetores_de_animais.api_controle_adocoes.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }
}

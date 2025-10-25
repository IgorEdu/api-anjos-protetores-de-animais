package br.com.anjos_protetores_de_animais.api_controle_adocoes.exception;

import org.springframework.http.HttpStatus;

public class AdopterNotFoundException extends BaseException {

    public AdopterNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }
}

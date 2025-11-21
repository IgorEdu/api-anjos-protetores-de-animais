package br.com.anjos_protetores_de_animais.api_controle_adocoes.exception;

import org.springframework.http.HttpStatus;

public class AnimalNotFoundException extends BaseException {

    public AnimalNotFoundException() {
        super("Animal not found", HttpStatus.NOT_FOUND);
    }
}

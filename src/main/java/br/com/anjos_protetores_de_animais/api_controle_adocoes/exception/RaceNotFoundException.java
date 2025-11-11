package br.com.anjos_protetores_de_animais.api_controle_adocoes.exception;

import org.springframework.http.HttpStatus;

public class RaceNotFoundException extends BaseException {

    public RaceNotFoundException() {
        super("Race not found", HttpStatus.NOT_FOUND);
    }
}

package br.com.anjos_protetores_de_animais.api_controle_adocoes.exception;

import org.springframework.http.HttpStatus;

public class AnimalAlreadyAdoptedException extends BaseException {

    public AnimalAlreadyAdoptedException() {
        super("Animal already adopted", HttpStatus.UNAUTHORIZED);
    }
}

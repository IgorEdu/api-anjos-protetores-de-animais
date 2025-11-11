package br.com.anjos_protetores_de_animais.api_controle_adocoes.exception;

import org.springframework.http.HttpStatus;

public class SpecieNotFoundException extends BaseException {

    public SpecieNotFoundException() {
        super("Specie not found", HttpStatus.NOT_FOUND);
    }
}

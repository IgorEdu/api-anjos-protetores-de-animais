package br.com.anjos_protetores_de_animais.api_controle_adocoes.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends Exception {

    @Getter
    private final HttpStatus status;

    public BaseException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }
}
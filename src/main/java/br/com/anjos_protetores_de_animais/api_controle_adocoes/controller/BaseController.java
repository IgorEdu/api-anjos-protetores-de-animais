package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.ApiError;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

public class BaseController {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(final BaseException e, final HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus())
                .body(ApiError.builder()
                        .timestamp(Instant.now())
                        .status(e.getStatus().value())
                        .error(e.getStatus().getReasonPhrase())
                        .message(e.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }
}

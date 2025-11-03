package br.com.anjos_protetores_de_animais.api_controle_adocoes.service;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;

import java.util.List;

public interface AnimalService {

    List<AnimalListDto> findAllAnimals();


}

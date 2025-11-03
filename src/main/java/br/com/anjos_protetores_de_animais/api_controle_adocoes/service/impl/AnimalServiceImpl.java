package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AnimalListDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Animal;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.AnimalRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AnimalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("animalService")
@Transactional(readOnly = true)
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository repository;

    public AnimalServiceImpl(final AnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AnimalListDto> findAllAnimals() {
        final List<Animal> animals = this.repository.findAll();

        return animals.stream()
            .map(AnimalListDto::toDto)
            .toList();
    }
}

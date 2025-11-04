package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.SpecieDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Specie;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.SpecieUpdatePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.SpecieRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.SpecieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("specieService")
@Transactional(readOnly = true)
public class SpecieServiceImpl implements SpecieService {

    private final SpecieRepository repository;

    public SpecieServiceImpl(final SpecieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SpecieDto> findAllSpecies() {
        final List<Specie> species = this.repository.findAll();

        return species.stream()
            .map(SpecieDto::toDto)
            .toList();
    }

    @Override
    public SpecieDto findSpecieById(UUID id) {
        final Specie specie = this.repository.findById(id).orElseThrow();

        return SpecieDto.toDto(specie);
    }

    @Override
    public ResponseEntity<?> createSpecie(SpecieUpdatePayload payload) {
        final String name = payload.getName();

        final Specie specie = new Specie(
                name
        );

        this.repository.save(specie);

        return ResponseEntity.ok(null);
    }
}

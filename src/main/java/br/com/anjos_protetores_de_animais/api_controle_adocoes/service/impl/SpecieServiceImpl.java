package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.SpecieDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.AbstractBaseEntity;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Race;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Specie;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.NamePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.SpecieNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.RaceRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.SpecieRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.SpecieService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("specieService")
@Transactional(readOnly = true)
public class SpecieServiceImpl implements SpecieService {

    private final SpecieRepository repository;

    private final RaceRepository raceRepository;

    public SpecieServiceImpl(final SpecieRepository repository,
                             final RaceRepository raceRepository) {
        this.repository = repository;
        this.raceRepository = raceRepository;
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
    @Transactional(rollbackFor = Exception.class)
    public SpecieDto create(@NotNull @NotNull final NamePayload payload) {
        final String name = payload.getName();

        final Specie specie = new Specie(name);
        final Specie createdSpecie = this.repository.save(specie);

        return SpecieDto.toDto(createdSpecie);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(@NotNull final UUID id) throws SpecieNotFoundException {
        this.repository.findById(id)
            .orElseThrow(SpecieNotFoundException::new);

        final List<Race> races = this.raceRepository.findAllBySpecieId(id);

        if (!races.isEmpty()) {
            final List<UUID> raceIds = races.stream().map(AbstractBaseEntity::getId).toList();
            this.raceRepository.deleteAllById(raceIds);
        }

        this.repository.deleteById(id);
    }
}

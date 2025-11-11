package br.com.anjos_protetores_de_animais.api_controle_adocoes.service.impl;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.RaceDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Race;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Specie;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.payload.NamePayload;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.RaceNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.exception.SpecieNotFoundException;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.RaceRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.repository.SpecieRepository;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.RaceService;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("raceService")
@Transactional(readOnly = true)
public class RaceServiceImpl implements RaceService {

    private final RaceRepository repository;

    private final SpecieRepository specieRepository;

    public RaceServiceImpl(final RaceRepository repository,
                           final SpecieRepository specieRepository) {
        this.repository = repository;
        this.specieRepository = specieRepository;
    }

    @Override
    public List<RaceDto> findAllBySpecie(@NonNull @NotNull final UUID specieId) {
        final List<Race> races = this.repository.findAllBySpecieId(specieId);

        return races.stream()
            .map(RaceDto::toDto)
            .toList();
    }

    @Override
    public RaceDto findRaceById(UUID id) {
        final Race race = this.repository.findById(id).orElseThrow();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RaceDto create(@NotNull @NotNull final NamePayload payload,
                          @NonNull @NotNull final UUID specieId) throws SpecieNotFoundException {
        final String name = payload.getName();

        final Specie specie = this.specieRepository.findById(specieId)
            .orElseThrow(SpecieNotFoundException::new);

        final Race race = new Race(specie, name);
        final Race createdRace = this.repository.save(race);

        return RaceDto.toDto(createdRace);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(@NonNull @NotNull final UUID id) throws RaceNotFoundException {
        this.repository.findById(id)
            .orElseThrow(RaceNotFoundException::new);

        this.repository.deleteById(id);
    }
}

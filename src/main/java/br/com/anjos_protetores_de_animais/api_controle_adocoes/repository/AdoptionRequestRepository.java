package br.com.anjos_protetores_de_animais.api_controle_adocoes.repository;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, UUID>, JpaSpecificationExecutor<AdoptionRequest> {

    List<AdoptionRequest> findAllWithAllDependenciesByAnimalId(final UUID animalId);
}

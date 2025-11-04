package br.com.anjos_protetores_de_animais.api_controle_adocoes.repository;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, UUID>, JpaSpecificationExecutor<Specie> {
}

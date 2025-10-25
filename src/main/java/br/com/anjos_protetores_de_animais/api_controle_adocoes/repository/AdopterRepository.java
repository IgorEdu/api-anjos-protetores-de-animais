package br.com.anjos_protetores_de_animais.api_controle_adocoes.repository;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, UUID>, JpaSpecificationExecutor<Adopter> {

    Optional<Adopter> findOneByEmail(final String email);

    Optional<Adopter> findOneByIdAndIsDeletedFalse(final UUID id);
}

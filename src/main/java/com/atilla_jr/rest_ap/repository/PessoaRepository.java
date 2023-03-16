package com.atilla_jr.rest_ap.repository;

import com.atilla_jr.rest_ap.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {
  boolean existsByIdAndUsuarioIsNotNullOrEnderecoIsNotNull(String id);
}

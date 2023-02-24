package com.atilla_jr.rest_ap.repository;

import com.atilla_jr.rest_ap.domain.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, String> {}

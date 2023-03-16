package com.atilla_jr.rest_ap.repository;

import com.atilla_jr.rest_ap.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {}

package com.atilla_jr.services;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.exception.ObjectNotFoundException;
import com.atilla_jr.rest_ap.repository.PessoaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

  @Autowired
  private PessoaRepository repo;

  public Iterable<Pessoa> findAll() {
    return repo.findAll();
  }

  public Pessoa findById(String id) {
    Optional<Pessoa> obj = repo.findById(id);
    return obj.orElseThrow(() ->
      new ObjectNotFoundException("Objeto não encontrado")
    );
  }
}

package com.atilla_jr.services;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.repository.PessoaRepository;
import com.atilla_jr.services.exception.ObjectNotFoundException;
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
    Pessoa pessoa = repo.findOne(id);
    if (pessoa == null) {
      throw new ObjectNotFoundException("Objeto não encontrado");
    }
    // Optional<Pessoa> obj = repo.findById(id);
    // return obj.orElseThrow(() ->
    //   new ObjectNotFoundException("Objeto não encontrado")
    //);
  }
}

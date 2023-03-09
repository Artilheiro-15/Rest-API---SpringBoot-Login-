package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.dto.PessoaDTO;
import com.atilla_jr.rest_ap.exception.ObjectNotFoundException;
import com.atilla_jr.rest_ap.repository.PessoaRepository;
import java.util.NoSuchElementException;
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

  public Pessoa save(Pessoa obj) {
    return repo.save(obj);
  }

  public void delete(String id) {
    findById(id);

    repo.deleteById(id);
  }

  public Pessoa update(Pessoa obj, Object id) {
    Pessoa newObj;
    try {
      newObj = repo.findById(obj.getId().toString()).get();
    } catch (NoSuchElementException e) {
      throw new ObjectNotFoundException("Objeto não encontrado");
    }
    updateData(newObj, obj);
    return repo.save(obj);
  }

  private void updateData(Pessoa newObj, Pessoa obj) {
    if (!(obj.getNome() != null)) {
      obj.setNome(newObj.getNome());
    }

    if (!(obj.getSobrenome() != null)) {
      obj.setSobrenome(newObj.getSobrenome());
    }

    // if (!(obj.getData_nascimento() != null)) {
    //   obj.setData_nascimento(newObj.getData_nascimento());
    //}
    if (!(obj.getGenero() != null)) {
      obj.setGenero(newObj.getGenero());
    }
    if (!(obj.getInscricao() != null)) {
      obj.setInscricao(newObj.getInscricao());
    }
  }

  public Pessoa fromDTO(PessoaDTO objDto) {
    return new Pessoa(
      objDto.getId(),
      objDto.getNome(),
      objDto.getSobrenome(),
      objDto.getGenero(),
      objDto.getData_nascimento(),
      objDto.getIncricao()
    );
  }
}

package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Endereco;
import com.atilla_jr.rest_ap.dto.EnderecoDTO;
import com.atilla_jr.rest_ap.exception.ObjectNotFoundException;
import com.atilla_jr.rest_ap.repository.EnderecoRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServices {

  @Autowired
  private EnderecoRepository repo;

  public Iterable<Endereco> findAll() {
    return repo.findAll();
  }

  public Endereco findById(String id) {
    Optional<Endereco> obj = repo.findById(id);
    return obj.orElseThrow(() ->
      new ObjectNotFoundException("Objeto não encontrado")
    );
  }

  public Endereco save(Endereco obj) {
    return repo.save(obj);
  }

  public void delete(String id) {
    findById(id);
    repo.deleteById(id);
  }

  public Endereco update(Endereco obj, Object id) {
    Endereco newObj;
    try {
      newObj = repo.findById(obj.getId().toString()).get();
    } catch (NoSuchElementException e) {
      throw new ObjectNotFoundException("Objeto não encontrado");
    }
    updateData(newObj, obj);
    return repo.save(obj);
  }

  private void updateData(Endereco newObj, Endereco obj) {
    if (!(obj.getLogadouro() != null)) {
      obj.setLogadouro(newObj.getLogadouro());
    }
    if (!(obj.getNumero() != null)) {
      obj.setNumero(newObj.getNumero());
    }
    if (!(obj.getComplemento() != null)) {
      obj.setComplemento(newObj.getComplemento());
    }
    if (!(obj.getBairro() != null)) {
      obj.setBairro(newObj.getBairro());
    }
    if (!(obj.getCidade() != null)) {
      obj.setCidade(newObj.getCidade());
    }
    if (!(obj.getEstado() != null)) {
      obj.setEstado(newObj.getEstado());
    }
    if (!(obj.getPais() != null)) {
      obj.setPais(newObj.getPais());
    }
    // if (!(obj.getPessoa_id() != null)) {
    //   obj.setPessoa_id(newObj.getPessoa_id());
    // }
    if (!(obj.getCreated_at() != null)) {
      obj.setCreated_at(newObj.getCreated_at());
    }
    if (!(obj.getUpdate_at() != null)) {
      obj.setUpdate_at(newObj.getUpdate_at());
    }
  }

  public Endereco fromDTO(EnderecoDTO objDto) {
    return new Endereco(
      objDto.getId(),
      objDto.getLogadouro(),
      objDto.getNumero(),
      objDto.getComplemento(),
      objDto.getBairro(),
      objDto.getCidade(),
      objDto.getEstado(),
      objDto.getPais(),
      objDto.getPessoa_id(),
      objDto.getCreated_at(),
      objDto.getUpdate_at()
    );
  }
}

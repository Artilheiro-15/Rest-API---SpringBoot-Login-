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

  //------------------------------------------------------------------

  public Endereco createEndereco(EnderecoDTO enderecoDTO) {
    var endereco = Endereco
      .builder()
      .id(enderecoDTO.getId())
      .logradouro(enderecoDTO.getLogradouro())
      .numero(enderecoDTO.getNumero())
      .complemento(enderecoDTO.getComplemento())
      .bairro(enderecoDTO.getBairro())
      .cidade(enderecoDTO.getCidade())
      .estado(enderecoDTO.getEstado())
      .pais(enderecoDTO.getPais())
      .build();

    return repo.save(endereco);
  }

  //-------------------------------------------------------------

  private void updateData(Endereco newObj, Endereco obj) {
    if (!(obj.getLogradouro() != null)) {
      obj.setLogadouro(newObj.getLogradouro());
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
    return Endereco
      .builder()
      .id(objDto.getId())
      .logradouro(objDto.getLogradouro())
      .numero(objDto.getNumero())
      .complemento(objDto.getComplemento())
      .bairro(objDto.getBairro())
      .cidade(objDto.getCidade())
      .estado(objDto.getEstado())
      .pais(objDto.getPais())
      .build();
  }
}
// public void create(Object endereco) {
//   return repo.create(endereco);
// }
// }

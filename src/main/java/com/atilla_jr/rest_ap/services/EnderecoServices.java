package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Endereco;
import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.domain.Usuario;
import com.atilla_jr.rest_ap.dto.EnderecoDTO;
import com.atilla_jr.rest_ap.exception.ObjectNotFoundException;
import com.atilla_jr.rest_ap.repository.EnderecoRepository;
import com.atilla_jr.rest_ap.repository.PessoaRepository;
import com.atilla_jr.rest_ap.repository.UsuarioRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServices {

  @Autowired
  private EnderecoRepository repo;

  @Autowired
  private PessoaRepository Pessoarepo;

  @Autowired
  private UsuarioRepository UserRepository;

  // public List<Endereco> findByLogradouroAndNumeroAndCidadeAndEstado(
  //   String logradouro,
  //   String numero,
  //   String cidade,
  //   String estado,
  //   String pessoa
  // ) {
  //   return repo.findByLogradouroAndNumeroAndCidadeAndEstado(
  //     logradouro,
  //     numero,
  //     cidade,
  //     estado

  //   );
  // }

  public List<Endereco> findByLogradouroAndNumeroAndCidadeAndEstadoAndPessoa(
    String logradouro,
    String numero,
    String cidade,
    String estado,
    Pessoa pessoa
  ) {
    return repo.findByLogradouroAndNumeroAndCidadeAndEstadoAndPessoa(
      logradouro,
      numero,
      cidade,
      estado,
      pessoa
    );
  }

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

  public boolean isAddressInUse(String id) {
    // Verifica se o endereço está sendo utilizado por uma pessoa
    List<Pessoa> peopleWithAddress = Pessoarepo.findByid(id);
    if (!peopleWithAddress.isEmpty()) {
      return true;
    }

    // Verifica se o endereço está sendo utilizado por um usuário
    List<Usuario> usersWithAddress = UserRepository.findByPessoa(id);
    if (!usersWithAddress.isEmpty()) {
      return true;
    }

    return false;
  }

  //================================================

  // public void removeEndereco(Pessoa pessoa) {
  //   Endereco endereco = pessoa.getEndereco();
  //   if (endereco != null) {
  //     endereco.setPessoa(null);
  //     pessoa.setEndereco(null);
  //     Pessoarepo.save(pessoa);
  //   }
  // }

  public void deleteByPessoaId(Integer id) {
    var endereco = repo
      .findByPessoaId(id)
      .orElseThrow(() -> new RuntimeException("Endereco não foi encontrada"));
    repo.deleteById(endereco.getId());
  }

  //=============================================

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
    if (!(obj.getPessoa() != null)) {
      obj.setPessoa(newObj.getPessoa());
    }
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

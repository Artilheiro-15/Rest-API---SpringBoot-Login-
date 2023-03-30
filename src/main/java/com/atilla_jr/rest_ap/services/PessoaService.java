package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.dto.PessoaDTO;
import com.atilla_jr.rest_ap.exception.ObjectNotFoundException;
import com.atilla_jr.rest_ap.repository.PessoaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.ZoneId;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ComponentScan
public class PessoaService {

  @Autowired
  private PessoaRepository repo;

  @Autowired
  private UsuarioServices usuarioServices;

  @Autowired
  private EnderecoServices enderecoServices;

  @PersistenceContext
  private EntityManager entityManager;

  // public boolean existsPessoaWithUsuarioOrEndereco(String id) {
  //   // return repo.existsByIdAndUsuarioIsNotNullOrEnderecoIsNotNull(id);
  // }

  public Iterable<Pessoa> findAll() {
    return repo.findAll();
  }

  public Pessoa findById(String id) {
    Optional<Pessoa> obj = repo.findById(id);
    return obj.orElseThrow(() ->
      new ObjectNotFoundException("Objeto não encontrado id: " + id)
    );
  }

  public Pessoa save(Pessoa obj) {
    return repo.save(obj);
  }

  public boolean pessoaExistsById(String id) {
    Optional<Pessoa> pessoaOptional = repo.findById(id);
    return pessoaOptional.isPresent();
  }

  // public Pessoa findByInscricao(String inscricao) {
  //   return repo.findByInscricao(inscricao);
  // }

  public boolean inscricaoExists(String inscricao) {
    return (
      repo
        .findByInscricao(inscricao)
        .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"))
        .getInscricao() !=
      null
    );
  }

  //=============================================================

  @Transactional
  public void delete(String id) {
    Pessoa pessoa = repo
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Pessoa não foi encontrada"));

    // Excluir os registros associados na tabela endereco
    enderecoServices.deleteByPessoaId(pessoa.getId());

    // Excluir a pessoa
    usuarioServices.deleteByPessoaId(pessoa.getId());

    repo.delete(pessoa);
  }

  // @Transactional
  // public void delete(String pessoaId) {
  //   // Obter a Pessoa a ser excluída e seus registros relacionados usando JPA
  //   Pessoa pessoa = entityManager.find(Pessoa.class, pessoaId);

  //   // Excluir a Pessoa e seus registros relacionados usando JPA e Hibernate
  //   entityManager.remove(pessoa);

  //   // Confirmar as alterações e atualizar o banco de dados
  //   entityManager.flush();
  // }

  //=============================================================
  //=============================================================

  // public void delete(String id) {
  //   Pessoa pessoa = repo
  //     .findById(id)
  //     .orElseThrow(() -> new RuntimeException("Pessoa não foi encontrada"));

  //   // primeiro remove as associações
  //   pessoa.setUsuario(null);
  //   pessoa.setEndereco(null);
  //   repo.save(pessoa);

  //   // depois exclui a pessoa
  //   repo.delete(pessoa);
  // }

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

  //==============================================================
  //==============================================================

  // private void updateData(Pessoa newObj, Pessoa obj) {
  //   newObj.setNome(obj.getNome());
  //   newObj.setSobrenome(obj.getSobrenome());
  //   newObj.setGenero(obj.getGenero());
  //   // remova a linha abaixo
  //   // newObj.setCreatedAt(obj.getCreatedAt());
  // }

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

  //==============================================================
  //==============================================================

  public Pessoa fromDTO(PessoaDTO objDto) {
    return Pessoa
      .builder()
      .id(objDto.getId())
      .nome(objDto.getNome())
      .sobrenome(objDto.getSobrenome())
      .genero(objDto.getGenero())
      .dataNascimento(
        objDto
          .getDataNascimento()
          .toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDateTime()
      )
      .inscricao(objDto.getInscricao())
      .build();
  }
}

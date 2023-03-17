package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.dto.PessoaDTO;
import com.atilla_jr.rest_ap.exception.ObjectNotFoundException;
import com.atilla_jr.rest_ap.repository.PessoaRepository;
import java.time.ZoneId;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan
public class PessoaService {

  @Autowired
  private PessoaRepository repo;

  // @Autowired
  // private UsuarioRepository usuarioRepository;

  @Autowired
  private UsuarioServices usuarioServices;

  public boolean existsPessoaWithUsuarioOrEndereco(String id) {
    return repo.existsByIdAndUsuarioIsNotNullOrEnderecoIsNotNull(id);
  }

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

  public void delete(String id) {
    Pessoa pessoa = repo
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Pessoa não foi encontrada"));
    usuarioServices.deleteByPessoaId(pessoa.getId());
    //   // Obtém o id do Usuario associado à Pessoa
    //   Integer usuarioId = pessoa.getUsuario().getId();

    //   // Remove a associação entre Usuario e Pessoa
    //   Usuario usuario = usuarioRepository
    //     .findById(usuarioId)
    //     .orElseThrow(() -> new RuntimeException("Usuário não foi encontrado"));
    //   usuario.getPessoa().remove(pessoa);

    //   // Remove a associação entre Endereco e Pessoa
    //   Endereco endereco = pessoa.getEndereco();
    //   endereco.setPessoa(null);

    //   // Exclui a entidade Pessoa da base de dados
    //   repo.delete(pessoa);
    // }

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

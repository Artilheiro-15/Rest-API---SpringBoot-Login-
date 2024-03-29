package com.atilla_jr.rest_ap.repository;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import com.atilla_jr.rest_ap.model.UsuarioModel;
// import java.util.Optional;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface UsuarioRepository
//   extends JpaRepository<UsuarioModel, Integer> {
//   public Optional<UsuarioModel> findByLogin(String login);
// }
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByEmail(String email);
  Optional<Usuario> findById(Integer usuarioId);
  Optional<Usuario> findByPessoaId(Integer pessoaId);

  Optional<Pessoa> findByPessoa(Integer id);

  List<Usuario> findByPessoa(String pessoaId);
}
// @Repository
// public interface PessoaRepository extends CrudRepository<Pessoa, String> {
//   boolean existsByIdAndUsuarioIsNotNullOrEnderecoIsNotNull(String id);
// }

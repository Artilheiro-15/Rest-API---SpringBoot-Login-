package com.atilla_jr.rest_ap.repository;

import com.atilla_jr.rest_ap.domain.Usuario;
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
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
  Optional<Usuario> findByEmail(String email);
}

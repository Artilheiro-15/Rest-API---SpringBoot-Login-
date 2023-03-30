package com.atilla_jr.rest_ap.repository;

import com.atilla_jr.rest_ap.domain.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {
  // boolean existsByIdAndUsuarioIsNotNullOrEnderecoIsNotNull(String id);

  List<Pessoa> findByid(String id);

  // Pessoa findByInscricao(String inscricao);
  Optional<Pessoa> findByInscricao(String inscricao);
  // @Transactional
  // void deletePessoaAndRelatedData(Integer pessoaId);
  //   @Transactional
  //   @Modifying
  //   @Query("DELETE FROM Pessoa p WHERE p.id = :pessoaId")
  //   void deletePessoaAndRelatedData(@Param("pessoaId") Long pessoaId);
  // }

}

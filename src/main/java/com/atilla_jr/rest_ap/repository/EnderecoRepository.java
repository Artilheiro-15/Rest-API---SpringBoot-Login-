package com.atilla_jr.rest_ap.repository;

import com.atilla_jr.rest_ap.domain.Endereco;
import com.atilla_jr.rest_ap.domain.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
//public interface EnderecoRepository extends CrudRepository<Endereco, String> {
// Endereco create(Object endereco);
//   @Transactional
//   public Endereco create(Endereco obj) {
//     return repo.save(obj);

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {
  @Override
  <S extends Endereco> S save(S entity);

  Optional<Endereco> deleteById(Integer EnderecoId);

  Optional<Endereco> findByPessoaId(Integer pessoaId);

  List<Endereco> findByLogradouroAndNumeroAndCidadeAndEstadoAndPessoa(
    String logradouro,
    String numero,
    String cidade,
    String estado,
    Pessoa pessoa
  );
}

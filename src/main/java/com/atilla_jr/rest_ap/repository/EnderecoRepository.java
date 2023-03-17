package com.atilla_jr.rest_ap.repository;

import com.atilla_jr.rest_ap.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
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
}

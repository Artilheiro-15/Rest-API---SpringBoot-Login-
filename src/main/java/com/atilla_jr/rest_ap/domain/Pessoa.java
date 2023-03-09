package com.atilla_jr.rest_ap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "pessoa")
@Table(name = "pessoa")
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String nome;

  @Column(unique = true, nullable = false)
  private String sobrenome;

  @Column(unique = true, nullable = false)
  private String genero;

  @Column(nullable = false)
  private LocalDateTime data_nascimento;

  @Column(unique = true, nullable = false)
  private String inscricao;

  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  @Column(nullable = false)
  private LocalDateTime update_at;

  //===================================================

  // @OneToMany(mappedBy = "pessoa")
  // private List<Endereco> enderecos = new ArrayList<>();

  //===================================================

  public Pessoa() {}

  public Pessoa(
    Integer id,
    String nome,
    String sobrenome,
    String genero,
    Date data_nascimento,
    String inscricao
  ) {
    this.id = id;
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.genero = genero;

    this.inscricao = inscricao;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getInscricao() {
    return inscricao;
  }

  public void setInscricao(String inscricao) {
    this.inscricao = inscricao;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  public LocalDateTime getupdate_at() {
    return update_at;
  }

  public void setupdate_at(LocalDateTime update_at) {
    this.update_at = update_at;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Pessoa other = (Pessoa) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    return true;
  }

  public Object orElseThrow(Object object) {
    return null;
  }
}

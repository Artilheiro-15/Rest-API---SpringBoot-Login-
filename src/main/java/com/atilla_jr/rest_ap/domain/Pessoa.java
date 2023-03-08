package com.atilla_jr.rest_ap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "pessoa")
public class Pessoa {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer id;

  private String nome;
  private String sobrenome;
  private String genero;
  private Date data_nascimento;
  private String inscricao;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime update_at;

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
    this.data_nascimento = data_nascimento;
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

  public Date getData_nascimento() {
    return data_nascimento;
  }

  public void setData_nascimento(Date data_nascimento) {
    this.data_nascimento = data_nascimento;
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

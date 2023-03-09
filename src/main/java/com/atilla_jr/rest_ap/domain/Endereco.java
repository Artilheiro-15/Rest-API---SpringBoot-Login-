package com.atilla_jr.rest_ap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "endereco")
public class Endereco implements Serializable {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer id;

  private String logadouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String estado;
  private String pais;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime update_at;

  //===================================================
  // @OneToOne
  // @PrimaryKeyJoinColumn
  // private Pessoa pessoa;

  //===================================================

  public Endereco() {}

  public Endereco(
    Integer id,
    String logadouro,
    String numero,
    String complemento,
    String bairrro,
    String cidade,
    String estado,
    String pais,
    Integer pessoa_id,
    LocalDateTime created_at,
    LocalDateTime update_at
  ) {
    this.id = id;
    this.logadouro = logadouro;
    this.numero = numero;
    this.complemento = complemento;
    this.bairro = bairrro;
    this.cidade = cidade;
    this.estado = estado;
    this.pais = pais;
    this.created_at = created_at;
    this.update_at = update_at;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLogadouro() {
    return logadouro;
  }

  public void setLogadouro(String logadouro) {
    this.logadouro = logadouro;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  public LocalDateTime getUpdate_at() {
    return update_at;
  }

  public void setUpdate_at(LocalDateTime update_at) {
    this.update_at = update_at;
  }
}

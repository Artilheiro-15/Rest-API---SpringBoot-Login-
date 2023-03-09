package com.atilla_jr.rest_ap.dto;

import com.atilla_jr.rest_ap.domain.Endereco;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class EnderecoDTO implements Serializable {

  private Integer id;
  private String logadouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String estado;
  private String pais;
  private Integer pessoa_id;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime update_at;

  public EnderecoDTO() {}

  public EnderecoDTO(Endereco obj) {
    this.id = obj.getId();
    this.logadouro = obj.getLogadouro();
    this.numero = obj.getNumero();
    this.complemento = obj.getComplemento();
    this.bairro = obj.getBairro();
    this.cidade = obj.getCidade();
    this.estado = obj.getEstado();
    this.pais = obj.getPais();
    //  this.pessoa_id = obj.getPessoa_id();
    this.created_at = obj.getCreated_at();
    this.update_at = obj.getUpdate_at();
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

  public Integer getPessoa_id() {
    return pessoa_id;
  }

  public void setPessoa_id(Integer pessoa_id) {
    this.pessoa_id = pessoa_id;
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

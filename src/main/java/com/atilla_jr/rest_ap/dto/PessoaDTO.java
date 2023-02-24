package com.atilla_jr.rest_ap.dto;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class PessoaDTO implements Serializable {

  private Integer id;
  private String nome;
  private String sobrenome;
  private String genero;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date data_nascimento;

  private String inscricao;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDateTime created_at;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDateTime update_at;

  public PessoaDTO() {}

  public PessoaDTO(Pessoa obj) {
    id = obj.getId();

    nome = obj.getnome();
    sobrenome = obj.getSobrenome();
    genero = obj.getGenero();
    data_nascimento = obj.getData_nascimento();
    inscricao = obj.getInscricao();
    created_at = obj.getCreated_at();
    update_at = obj.getupdate_at();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getnome() {
    return nome;
  }

  public void setnome(String nome) {
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

  public String getIncricao() {
    return inscricao;
  }

  public void setIncricao(String incricao) {
    this.inscricao = incricao;
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
}

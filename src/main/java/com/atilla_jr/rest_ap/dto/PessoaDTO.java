package com.atilla_jr.rest_ap.dto;

import com.atilla_jr.rest_ap.domain.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class PessoaDTO implements Serializable {

  private Integer id;
  private String name;
  private String sobrenome;
  private String genero;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date data_nascimento;

  private String incricao;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDateTime created_at;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDateTime updated_at;

  public PessoaDTO() {}

  public PessoaDTO(Pessoa obj) {
    id = obj.getId();

    name = obj.getName();
    sobrenome = obj.getSobrenome();
    genero = obj.getGenero();
    data_nascimento = obj.getData_nascimento();
    incricao = obj.getInscricao();
    created_at = obj.getCreated_at();
    updated_at = obj.getUpdated_at();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    return incricao;
  }

  public void setIncricao(String incricao) {
    this.incricao = incricao;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  public LocalDateTime getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(LocalDateTime updated_at) {
    this.updated_at = updated_at;
  }
}

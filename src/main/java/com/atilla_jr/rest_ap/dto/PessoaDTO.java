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
  private Date dataNascimento;

  private String inscricao;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDateTime created_at;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDateTime update_at;

  public PessoaDTO() {}

  public PessoaDTO(Pessoa obj) {
    id = obj.getId();

    nome = obj.getNome();
    sobrenome = obj.getSobrenome();
    genero = obj.getGenero();
    //  data_nascimento = obj.getData_nascimento();
    inscricao = obj.getInscricao();
    created_at = obj.getCreated_at();
    update_at = obj.getUpdate_at();
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

  public void setSobreNome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date data_nascimento) {
    this.dataNascimento = data_nascimento;
  }

  public String getInscricao() {
    return inscricao;
  }

  public void setInscricao(String incricao) {
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

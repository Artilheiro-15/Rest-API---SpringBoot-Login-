package com.atilla_jr.rest_ap.domain;

import com.atilla_jr.rest_ap.domain.Usuario;
import com.atilla_jr.rest_ap.repository.PessoaRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name = "endereco")
@Table(name = "endereco")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

  // @Autowired
  // private PessoaRepository repo;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  private String logradouro;

  @Column(nullable = false)
  private String numero;

  @Column(nullable = false)
  private String complemento;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  private String cidade;

  @Column(nullable = false)
  private String estado;

  @Column(nullable = false)
  private String pais;

  //-----------------------------------------------------

  // @OneToOne
  // @JoinColumn(name = "pessoa_id", nullable = false)
  // private Pessoa pessoa_id;

  // @Column(name = "pessoa_id", insertable = false, updatable = false)
  // private Long pessoaId;

  //-------------------------------------
  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime update_at;

  //===================================================
  //===================================================
  // @ManyToOne
  // @JoinColumn(name = "pessoa_id")
  // private Pessoa pessoa;

  @OneToOne
  @JoinColumn(name = "pessoa_id", nullable = false)
  private Pessoa pessoa;

  // @ManyToOne
  // @JoinColumn(name = "usuario_id")
  // private Usuario usuario;

  // @OneToOne
  // @JoinColumn(name = "pessoa_id", nullable = false)
  // private Pessoa pessoa_id;

  //===================================================
  //===================================================

  // public void setUsuario(Usuario usuario) {
  //   this.usuario = usuario;
  // }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogadouro(String logradouro) {
    this.logradouro = logradouro;
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
  // public void removeEndereco(Pessoa pessoa) {
  //   Endereco endereco = pessoa.getEndereco();
  //   if (endereco != null) {
  //     endereco.setPessoa(null);
  //     pessoa.setEndereco(null);
  //     repo.save(pessoa);
  //   }
  // }
}

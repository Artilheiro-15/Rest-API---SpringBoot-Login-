package com.atilla_jr.rest_ap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "pessoa")
@Table(name = "pessoa")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String sobrenome;

  @Column(nullable = false)
  private String genero;

  @Column(nullable = false, name = "data_nascimento")
  private LocalDateTime dataNascimento;

  @Column(unique = true, nullable = false)
  private String inscricao;

  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  @Column(nullable = false)
  private LocalDateTime update_at;

  //===================================================

  @OneToOne
  private Endereco endereco;

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  @OneToOne
  private Usuario usuario;

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  //===================================================

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
}

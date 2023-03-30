package com.atilla_jr.rest_ap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String email;

  private String senha;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime created_at;

  @UpdateTimestamp
  private LocalDateTime update_at;

  //===================================================

  @OneToOne
  @JoinColumn(name = "pessoa_id", nullable = false)
  private Pessoa pessoa;

  //===================================================

  // @OneToOne
  // @JoinColumn(name = "endereco_id", nullable = false)
  // private Endereco endereco;

  //===================================================

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("default"));
  }

  @Override
  public String getPassword() {
    return this.senha;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  //===================================================

  public static class UsuarioBuilderJava {

    private Integer id;
    private String email;
    private String senha;
    private Pessoa pessoa;
    // private Endereco endereco; // novo campo
    private LocalDateTime created_at;
    private LocalDateTime update_at;

    public UsuarioBuilderJava id(Integer id) {
      this.id = id;
      return this;
    }

    public UsuarioBuilderJava email(String email) {
      this.email = email;
      return this;
    }

    public UsuarioBuilderJava senha(String senha) {
      this.senha = senha;
      return this;
    }

    public UsuarioBuilderJava pessoa(Pessoa pessoa) {
      this.pessoa = pessoa;
      return this;
    }

    // // novo método
    // public UsuarioBuilderJava endereco(Endereco endereco) {
    //   this.endereco = endereco;
    //   return this;
    // }

    public UsuarioBuilderJava created_at(LocalDateTime created_at) {
      this.created_at = created_at;
      return this;
    }

    public UsuarioBuilderJava update_at(LocalDateTime update_at) {
      this.update_at = update_at;
      return this;
    }

    public Usuario build() {
      Usuario usuario = new Usuario();
      usuario.setId(this.id);
      usuario.setEmail(this.email);
      usuario.setSenha(this.senha);
      usuario.setPessoa(this.pessoa);
      //  usuario.setEndereco(this.endereco); // define o endereço
      usuario.setCreated_at(this.created_at);
      usuario.setUpdate_at(this.update_at);
      return usuario;
    }
  }
}

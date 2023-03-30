package com.atilla_jr.rest_ap.security;

import com.atilla_jr.rest_ap.domain.Usuario;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

  private final Usuario usuario;

  public UserDetailsImpl(Usuario usuario) {
    this.usuario = usuario;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // Aqui você pode retornar uma lista de permissões/grupos do usuário, se houver
    return null;
  }

  @Override
  public String getPassword() {
    return usuario.getSenha();
  }

  @Override
  public String getUsername() {
    return usuario.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true; // Aqui você pode adicionar alguma lógica para verificar se a conta do usuário ainda é válida
  }

  @Override
  public boolean isAccountNonLocked() {
    return true; // Aqui você pode adicionar alguma lógica para verificar se a conta do usuário está bloqueada
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true; // Aqui você pode adicionar alguma lógica para verificar se as credenciais do usuário ainda são válidas
  }

  @Override
  public boolean isEnabled() {
    return true; // Aqui você pode adicionar alguma lógica para verificar se a conta do usuário está habilitada
  }
}

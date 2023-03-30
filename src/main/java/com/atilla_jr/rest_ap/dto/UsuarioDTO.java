package com.atilla_jr.rest_ap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

  private Integer id;
  private String email;
  private String senha;

  public String getSenha() {
    return this.senha;
  }
}

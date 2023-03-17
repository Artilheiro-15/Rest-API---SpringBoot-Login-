package com.atilla_jr.rest_ap.dto;

import com.atilla_jr.rest_ap.domain.Endereco;
import com.atilla_jr.rest_ap.domain.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {

  @NotNull
  @Size(
    min = 5,
    max = 32,
    message = "password size should be between" + " 5 and 32 digit or character"
  )
  private String email;

  @NotNull(message = "can't be null to signIn with knew user")
  @Size(
    min = 5,
    max = 32,
    message = "password size should be between" + " 5 and 32 digit or character"
  )
  private String senha;

  private PessoaDTO pessoa;

  private EnderecoDTO enderecoDto;

  private UsuarioDTO usuarioDto;

  private Usuario usuario;

  private Endereco endereco;
}

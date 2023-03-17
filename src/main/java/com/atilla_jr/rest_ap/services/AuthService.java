package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Endereco;
import com.atilla_jr.rest_ap.domain.Usuario;
import com.atilla_jr.rest_ap.dto.UserRequestDTO;
import com.atilla_jr.rest_ap.dto.UserResponseDTO;
import com.atilla_jr.rest_ap.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  @Autowired
  private UsuarioServices usuarioServices;

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private EnderecoServices enderecoServices;

  @Autowired
  private AuthenticationManager authenticationManager;

  public UserResponseDTO register(UserRequestDTO request) {
    var pessoa = pessoaService.save(pessoaService.fromDTO(request.getPessoa())); ////////////////////////

    //var endereco = EnderecoServices.save(usuarioServices.fromDTO(request.getUsuario()));

    var endereco = Endereco
      .builder()
      .estado(request.getEndereco().getEstado())
      .complemento(request.getEndereco().getComplemento())
      .logradouro(request.getEndereco().getLogradouro())
      .numero(request.getEndereco().getNumero())
      .bairro(request.getEndereco().getBairro())
      .cidade(request.getEndereco().getCidade())
      .pais(request.getEndereco().getPais())
      .pessoa(pessoa)
      .build();

    enderecoServices.save(endereco);

    var user = Usuario
      .builder()
      .email(request.getEmail())
      .senha(request.getSenha())
      .pessoa(pessoa)
      .build();

    usuarioServices.create(user);
    // repository.save(user);
    // var jwtToken = jwtService.generateToken()
    var jwtToken = jwtService.generateToken(user);

    return UserResponseDTO
      .builder()
      .email(user.getEmail())
      .token(jwtToken)
      .build();
  }

  //------------------------------------------------------

  public UserResponseDTO authenticate(UserRequestDTO request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getSenha()
      )
    );
    var user = usuarioServices.findByEmail(request.getEmail());
    var jwtToken = jwtService.generateToken(user);

    return UserResponseDTO.builder().token(jwtToken).build();
    //}

    // if (user == null || jwtToken == null) {
    //   System.out.println("Authenticate falhou");
    // } else {
    //   return UserResponseDTO.builder().token(jwtToken).build();
    // }
    // return null;
  }
}

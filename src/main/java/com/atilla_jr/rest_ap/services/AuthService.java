package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Endereco;
import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.domain.Usuario;
import com.atilla_jr.rest_ap.dto.ErrorResponseDTO;
import com.atilla_jr.rest_ap.dto.PessoaDTO;
import com.atilla_jr.rest_ap.dto.UserRequestDTO;
import com.atilla_jr.rest_ap.dto.UserResponseDTO;
import com.atilla_jr.rest_ap.repository.UsuarioRepository;
import com.atilla_jr.rest_ap.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
//@Slf4j
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

  @Autowired
  private UsuarioRepository usuarioRepository;

  //==========================================================
  //==========================================================
  public boolean userExists(String username) {
    return usuarioRepository.findByEmail(username).isPresent();
  }

  public Usuario save(Usuario obj) {
    return usuarioServices.save(obj);
  }

  //==========================================================
  //==========================================================

  public UserResponseDTO register(UserRequestDTO request) throws Exception {
    // Verifica se o email já está registrado
    if (userExists(request.getEmail())) {
      throw new Exception("Já existe um usuário registrado com este email.");
    }

    // Verifica se a pessoa já está registrada
    PessoaDTO pessoa = request.getPessoa();
    if (
      pessoa != null &&
      pessoa.getId() != null &&
      !pessoaService.pessoaExistsById(pessoa.getId().toString())
    ) {
      throw new Exception("Pessoa não encontrada.");
    }

    // Cria um novo registro de pessoa
    Pessoa novaPessoa = pessoaService.save(pessoaService.fromDTO(pessoa));

    // Cria um novo registro de endereço
    Endereco novoEndereco = Endereco
      .builder()
      .estado(request.getEndereco().getEstado())
      .complemento(request.getEndereco().getComplemento())
      .logradouro(request.getEndereco().getLogradouro())
      .numero(request.getEndereco().getNumero())
      .bairro(request.getEndereco().getBairro())
      .cidade(request.getEndereco().getCidade())
      .pais(request.getEndereco().getPais())
      .pessoa(novaPessoa)
      .build();

    enderecoServices.save(novoEndereco);

    // Cria um novo registro de usuário
    Usuario novoUsuario = Usuario
      .builder()
      .email(request.getEmail())
      .senha(request.getSenha())
      .pessoa(novaPessoa)
      .build();

    usuarioServices.save(novoUsuario);

    // Gera um novo token JWT para o novo usuário
    String token = jwtService.generateToken(novoUsuario);

    // Retorna o email do usuário e o token gerado
    return UserResponseDTO
      .builder()
      .email(novoUsuario.getEmail())
      .token(token)
      .build();
  }

  //==========================================================
  //==========================================================

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

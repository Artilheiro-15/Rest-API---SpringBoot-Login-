package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Usuario;
import com.atilla_jr.rest_ap.dto.AuthRequestDTO;
import com.atilla_jr.rest_ap.dto.AuthResponseDTO;
import com.atilla_jr.rest_ap.repository.UsuarioRepository;
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
  private UsuarioRepository usuarioRepository;

  @Autowired
  private JwtService jwtService;

  private AuthenticationManager authenticationManager;

  public AuthResponseDTO register(AuthRequestDTO request) {
    var user = Usuario
      .builder()
      .email(request.getEmail())
      .senha(request.getSenha())
      .build();

    usuarioRepository.save(user);
    // repository.save(user);
    // var jwtToken = jwtService.generateToken()
    var jwtToken = jwtService.generateToken(user);

    return AuthResponseDTO.builder().token(jwtToken).build();
  }

  public AuthResponseDTO authenticate(AuthRequestDTO request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getSenha()
      )
    );
    var user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);

    return AuthResponseDTO.builder().token(jwtToken).build();
  }
}

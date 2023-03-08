package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Usuario;
import com.atilla_jr.rest_ap.dto.UsuarioDTO;
import com.atilla_jr.rest_ap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServices {

  @Autowired
  private UsuarioRepository repo;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional(rollbackFor = Exception.class)
  public Integer saveDto(UsuarioDTO usuarioDTO) {
    usuarioDTO.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));

    return save(fromDTO(usuarioDTO)).getId();
  }

  public Usuario save(Usuario obj) {
    return repo.save(obj);
  }

  public Usuario fromDTO(UsuarioDTO objDto) {
    return Usuario
      .builder()
      .id(objDto.getId())
      .email(objDto.getEmail())
      .senha(objDto.getSenha())
      .build();
  }
}

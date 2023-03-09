package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Usuario;
import com.atilla_jr.rest_ap.dto.UsuarioDTO;
import com.atilla_jr.rest_ap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServices {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder bCryptPasswordEncoder;

  @Transactional(rollbackFor = Exception.class)
  public Integer saveDto(UsuarioDTO usuarioDTO) {
    usuarioDTO.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));

    return create(fromDTO(usuarioDTO)).getId();
  }

  @Transactional
  public Usuario create(Usuario obj) {
    obj.setSenha(bCryptPasswordEncoder.encode(obj.getSenha()));
    return usuarioRepository.save(obj);
  }

  // @Transactional
  // public Usuario create(Usuario obj) {
  //   obj.setId(null);

  //   // obj.setProfiles(
  //   //   Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet())
  //   // );
  //   obj = this.usuarioRepository.save(obj);
  //   return obj;
  // }

  @Transactional
  public Usuario update(Usuario obj) {
    Usuario newObj = findById(obj.getId());
    newObj.setSenha(obj.getPassword());
    newObj.setSenha(bCryptPasswordEncoder.encode(obj.getPassword()));
    return this.usuarioRepository.save(newObj);
  }

  private Usuario findById(Integer id) {
    return null;
  }

  public Usuario findByEmail(String email) {
    return this.usuarioRepository.findByEmail(email).orElseThrow();
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

package com.atilla_jr.rest_ap.services;

import com.atilla_jr.rest_ap.domain.Endereco;
import com.atilla_jr.rest_ap.domain.Pessoa;
import com.atilla_jr.rest_ap.domain.Usuario;
import com.atilla_jr.rest_ap.dto.UserResponseDTO;
import com.atilla_jr.rest_ap.dto.UsuarioDTO;
import com.atilla_jr.rest_ap.repository.PessoaRepository;
import com.atilla_jr.rest_ap.repository.UsuarioRepository;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServices implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private PessoaRepository pessoareposRepository;

  @Autowired
  public UsuarioServices(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Transactional(rollbackFor = Exception.class)
  public Integer saveDto(UsuarioDTO usuarioDTO) {
    usuarioDTO.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));

    return create(fromDTO(usuarioDTO)).getId();
  }

  public Usuario save(Usuario obj) {
    return usuarioRepository.save(obj);
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

  public void deleteByPessoaId(Integer id) {
    var user = usuarioRepository
      .findByPessoaId(id)
      .orElseThrow(() -> new RuntimeException("Usuario não foi encontrada"));
    usuarioRepository.deleteById(user.getId());
  }

  public UserDetails findByEmail(String email) {
    var usuario = usuarioRepository
      .findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")
      );

    return usuario;
  }

  // public void deleteByPessoaId(Integer id) {
  //   Usuario usuario = usuarioRepository.findByPessoaId(id).get();
  //   if (usuario != null) {
  //     removeEnderecoFromUsuario(usuario);
  //     usuario.setPessoa(null);
  //     usuarioRepository.save(usuario);
  //   }
  // }

  // //================================================

  // public void removeEndereco(Pessoa pessoa) {
  //   Endereco endereco = pessoa.getEndereco();
  //   if (endereco != null) {
  //     endereco.setPessoa(null);
  //     pessoa.setEndereco(null);
  //     Pessoarepo.save(pessoa);
  //   }
  // }

  // public void removeEnderecoFromUsuario(Usuario usuario) {
  //   if (
  //     usuario.getPessoa() != null && usuario.getPessoa().getEndereco() != null
  //   ) {
  //     usuario.getPessoa().setEndereco(null);
  //     usuarioRepository.save(usuario);
  //   }
  // }

  //=============================================

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

  // public Usuario findByEmail(String email) {
  //   return this.usuarioRepository.findByEmail(email).orElseThrow();
  // }

  public Usuario fromDTO(UsuarioDTO objDto) {
    return Usuario
      .builder()
      .id(objDto.getId())
      .email(objDto.getEmail())
      .senha(objDto.getSenha())
      .build();
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'loadUserByUsername'"
    );
  }
}

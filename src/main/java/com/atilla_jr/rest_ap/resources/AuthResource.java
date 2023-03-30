package com.atilla_jr.rest_ap.resources;

import com.atilla_jr.rest_ap.domain.Usuario;
import com.atilla_jr.rest_ap.dto.UserRequestDTO;
import com.atilla_jr.rest_ap.dto.UserResponseDTO;
import com.atilla_jr.rest_ap.dto.UsuarioDTO;
import com.atilla_jr.rest_ap.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

  @Autowired
  private AuthService authService;

  // @GetMapping("/user/{userId}")
  //public ResponseEntity<List<QuestionAndAnswerDTO>> getUserAnsweredQuestions(@PathVariable Long userId) {
  // List<QuestionAndAnswerDTO> userAnsweredQuestions = answerService.getUserAnsweredQuestions(userId);
  //  return ResponseEntity.ok(userAnsweredQuestions);

  @PostMapping("/register")
  public ResponseEntity<UserResponseDTO> register(
    @RequestBody UserRequestDTO request
  ) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponseDTO> authenticate(
    @RequestBody UserRequestDTO request
  ) {
    return ResponseEntity.ok(authService.authenticate(request));
  }
}

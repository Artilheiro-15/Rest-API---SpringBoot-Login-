package com.atilla_jr.rest_ap.resources;

import com.atilla_jr.rest_ap.dto.UserRequestDTO;
import com.atilla_jr.rest_ap.dto.UserResponseDTO;
import com.atilla_jr.rest_ap.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
  private AuthService service;

  // @GetMapping("/user/{userId}")
  //public ResponseEntity<List<QuestionAndAnswerDTO>> getUserAnsweredQuestions(@PathVariable Long userId) {
  // List<QuestionAndAnswerDTO> userAnsweredQuestions = answerService.getUserAnsweredQuestions(userId);
  //  return ResponseEntity.ok(userAnsweredQuestions);

  @PostMapping("/register")
  public ResponseEntity<UserResponseDTO> register(
    @RequestBody UserRequestDTO request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponseDTO> authenticate(
    @RequestBody UserRequestDTO request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}

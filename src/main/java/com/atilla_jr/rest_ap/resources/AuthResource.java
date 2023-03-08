package com.atilla_jr.rest_ap.resources;

import com.atilla_jr.rest_ap.dto.AuthRequestDTO;
import com.atilla_jr.rest_ap.dto.AuthResponseDTO;
import com.atilla_jr.rest_ap.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

  @Autowired
  private AuthService service;

  @PostMapping("/register")
  public ResponseEntity<AuthResponseDTO> register(
    @RequestBody AuthRequestDTO request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> authenticate(
    @RequestBody AuthRequestDTO request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}

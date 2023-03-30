package com.atilla_jr.rest_ap.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JwtExceptionHandler implements AuthenticationEntryPoint {

  // @ExceptionHandler(TokenInvalidException.class)
  // public ResponseStatusException handleTokenInvalidException(
  //   TokenInvalidException ex
  // ) {
  //   return new ResponseStatusException(
  //     HttpStatus.UNAUTHORIZED,
  //     ex.getMessage()
  //   );
  // }

  // @ExceptionHandler(ExpiredJwtException.class)
  // public ResponseStatusException handleTokenExpiredException(
  //   ExpiredJwtException ex
  // ) {
  //   return new ResponseStatusException(
  //     HttpStatus.UNAUTHORIZED,
  //     ex.getMessage()
  //   );
  // }

  // @ExceptionHandler(Exception.class)
  // public ResponseStatusException handleException(Exception ex) {
  //   return new ResponseStatusException(
  //     HttpStatus.INTERNAL_SERVER_ERROR,
  //     ex.getMessage()
  //   );
  // }

  @Override
  public void commence(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException authException
  ) throws IOException, ServletException {
    // 401
    response.sendError(
      HttpServletResponse.SC_UNAUTHORIZED,
      "Falha na autorização : "
    );
  }

  @ExceptionHandler(value = { AccessDeniedException.class })
  public void commence(
    HttpServletRequest request,
    HttpServletResponse response,
    AccessDeniedException accessDeniedException
  ) throws IOException {
    // 401
    response.sendError(
      HttpServletResponse.SC_UNAUTHORIZED,
      "Falha na autorização : " + accessDeniedException.getMessage()
    );
  }
}

package com.atilla_jr.rest_ap.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class TratamentoError403 implements AccessDeniedHandler {

  @Override
  public void handle(
    HttpServletRequest req,
    HttpServletResponse res,
    AccessDeniedException ex
  ) throws IOException, ServletException {
    res.setStatus(403);
    res.setContentType("application/json");
    res.getWriter().write("Voce nao em autorizaçao ");
  }
}

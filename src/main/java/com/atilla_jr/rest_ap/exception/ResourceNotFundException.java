package com.atilla_jr.rest_ap.exception;

public class ResourceNotFundException extends RuntimeException {

  public ResourceNotFundException(Object id) {
    super("Objeto Nao Encontrado id: " + id);
  }
}

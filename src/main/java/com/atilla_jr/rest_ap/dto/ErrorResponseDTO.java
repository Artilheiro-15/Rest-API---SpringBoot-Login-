package com.atilla_jr.rest_ap.dto;

public class ErrorResponseDTO {

  private String message;
  private Integer status;

  public ErrorResponseDTO(String message, Integer status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public Integer getStatus() {
    return status;
  }

  public static ErrorResponseDTOBuilder builder() {
    return new ErrorResponseDTOBuilder();
  }

  public static class ErrorResponseDTOBuilder {

    private String message;
    private Integer status;

    public ErrorResponseDTOBuilder message(String message) {
      this.message = message;
      return this;
    }

    public ErrorResponseDTOBuilder status(Integer status) {
      this.status = status;
      return this;
    }

    public ErrorResponseDTO build() {
      return new ErrorResponseDTO(message, status);
    }
  }
}

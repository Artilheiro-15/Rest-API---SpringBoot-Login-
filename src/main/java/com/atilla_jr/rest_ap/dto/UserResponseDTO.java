package com.atilla_jr.rest_ap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

  private String email;

  private String token;

  private String message;

  //private ErrorResponseDTO error; // Adiciona a propriedade de erro

  public UserResponseDTO(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
//   public ErrorResponseDTO getError() {
//     return error;
//   }
//   public void setError(ErrorResponseDTO error) {
//     this.error = error;
//   }
//   public static UserResponseDTOBuilder builder() {
//     return new UserResponseDTOBuilder();
//   }
//   public static class UserResponseDTOBuilder {
//     private String email;
//     private String token;
//     private ErrorResponseDTO error; // Adiciona a propriedade de erro
//     public UserResponseDTOBuilder email(String email) {
//       this.email = email;
//       return this;
//     }
//     public UserResponseDTOBuilder token(String token) {
//       this.token = token;
//       return this;
//     }
//     public UserResponseDTOBuilder errorResponse(ErrorResponseDTO error) {
//       this.error = error;
//       return this;
//     }
//     public UserResponseDTO build() {
//       UserResponseDTO userResponseDTO = new UserResponseDTO();
//       // userResponseDTO.setEmail(this.email);
//       // userResponseDTO.setToken(this.token);
//       userResponseDTO.setError(this.error);
//       return userResponseDTO;
//     }
//   }
// }

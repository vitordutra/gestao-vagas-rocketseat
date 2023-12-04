package br.com.vitordutra.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

  private UUID id;

  private String name;

  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
  private String username;

  @Email(message = "O campo [email] deve conter um email válido")
  private String email;

  @Length(min = 8, max = 128, message = "O campo senha deve ter no mínimo 8 e no máximo 128 caracteres")
  private String password;

  private String description;

  private String curriculum;

}

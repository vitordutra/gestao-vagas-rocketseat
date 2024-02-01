package br.com.vitordutra.gestao_vagas.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Vitor Dutra", requiredMode = Schema.RequiredMode.REQUIRED, description = "Candidate's name")
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Schema(example = "vitordutra", requiredMode = Schema.RequiredMode.REQUIRED, description = "Candidate's username")
    private String username;

    @Email(message = "O campo [email] deve conter um email válido")
    @Schema(example = "vitordutra@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "Candidate's email")
    private String email;

    @Length(min = 8, max = 128, message = "O campo senha deve ter no mínimo 8 e no máximo 128 caracteres")
    @Schema(example = "admin@1234", minLength = 8, maxLength = 128, requiredMode = Schema.RequiredMode.REQUIRED, description = "Candidate's password")
    private String password;

    @Schema(example = "Java Developer", description = "Candidate's brief description")
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

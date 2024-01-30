package br.com.vitordutra.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {

  private UUID id;
  @Schema(example = "vitordutra")
  private String username;
  @Schema(example = "vitordutra@gmail.com")
  private String email;
  @Schema(example = "Vitor Dutra")
  private String name;
  @Schema(example = "Java Developer")
  private String description;

}

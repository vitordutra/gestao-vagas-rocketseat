package br.com.vitordutra.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.vitordutra.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.vitordutra.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID candidateId) {
    var candidate = this.candidateRepository.findById(candidateId)
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("User not found");
        });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
        .id(candidate.getId())
        .username(candidate.getUsername())
        .email(candidate.getEmail())
        .name(candidate.getName())
        .description(candidate.getDescription())
        .build();

    return candidateDTO;
  }

}

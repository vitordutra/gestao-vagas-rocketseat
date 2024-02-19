package br.com.vitordutra.gestao_vagas.modules.candidate.useCases;

import br.com.vitordutra.gestao_vagas.exceptions.UserNotFoundException;
import br.com.vitordutra.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.vitordutra.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
                .orElseThrow(UserNotFoundException::new);

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

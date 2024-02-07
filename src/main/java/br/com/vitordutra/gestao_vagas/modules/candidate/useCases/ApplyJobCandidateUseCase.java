package br.com.vitordutra.gestao_vagas.modules.candidate.useCases;

import br.com.vitordutra.gestao_vagas.exceptions.JobNotFoundException;
import br.com.vitordutra.gestao_vagas.exceptions.UserNotFoundException;
import br.com.vitordutra.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.vitordutra.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.vitordutra.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    // Id do candidato
    // Id da vaga
    public void execute(UUID candidateId, UUID jobId) {
        // Validar se o candidato existe
        this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> new UserNotFoundException());
        // Validar se a vaga existe
        this.jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException());
        // Candidato se inscreve na vaga
    }

}

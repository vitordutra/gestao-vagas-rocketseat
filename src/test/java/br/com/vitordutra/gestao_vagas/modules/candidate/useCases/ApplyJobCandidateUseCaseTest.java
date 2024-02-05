package br.com.vitordutra.gestao_vagas.modules.candidate.useCases;

import br.com.vitordutra.gestao_vagas.exceptions.JobNotFoundException;
import br.com.vitordutra.gestao_vagas.exceptions.UserNotFoundException;
import br.com.vitordutra.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.vitordutra.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.vitordutra.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply for a job when candidate not found")
    public void shouldNotBeAbleToApplyForAJobWhenCandidateNotFound() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply for a job when job not found")
    public void shouldNotBeAbleToApplyForAJobWhenJobNotFound() {
        var candidateId = java.util.UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));
        try {
            applyJobCandidateUseCase.execute(candidateId, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
}

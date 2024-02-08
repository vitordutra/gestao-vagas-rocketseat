package br.com.vitordutra.gestao_vagas.modules.candidate.useCases;

import br.com.vitordutra.gestao_vagas.exceptions.JobNotFoundException;
import br.com.vitordutra.gestao_vagas.exceptions.UserNotFoundException;
import br.com.vitordutra.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.vitordutra.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.vitordutra.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.vitordutra.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.vitordutra.gestao_vagas.modules.company.entities.JobEntity;
import br.com.vitordutra.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

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

    @Test
    public void shouldBeAbleToCreateANewApplyJob() {
        var candidateId = java.util.UUID.randomUUID();
        var jobId = java.util.UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        var applyJobCreated = ApplyJobEntity.builder()
                .id(UUID.randomUUID())
                .build();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}

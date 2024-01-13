package br.com.vitordutra.gestao_vagas.modules.company.controllers;

import br.com.vitordutra.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.vitordutra.gestao_vagas.modules.company.entities.JobEntity;
import br.com.vitordutra.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id").toString();

    // jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
    var jobEntity = JobEntity.builder()
            .benefits(createJobDTO.getBenefits())
            .description(createJobDTO.getDescription())
            .level(createJobDTO.getLevel())
            .companyId(UUID.fromString(companyId))
            .build();

    return this.createJobUseCase.execute(jobEntity);
  }
}

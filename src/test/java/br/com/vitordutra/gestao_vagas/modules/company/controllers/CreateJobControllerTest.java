package br.com.vitordutra.gestao_vagas.modules.company.controllers;

import br.com.vitordutra.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.vitordutra.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.vitordutra.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.vitordutra.gestao_vagas.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void ShouldBeAbleToCreateANewJob() throws Exception {

        var company = CompanyEntity.builder()
                .description("DESCRIPTION_TEST")
                .name("NAME_TEST")
                .username("USERNAME_TEST")
                .email("EMAIL@TEST.COM")
                .password("PASSWORD_TEST")
                .build();

        company = companyRepository.saveAndFlush(company);

        var createdJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createdJobDTO))
                .header("Authorization", TestUtils.generateToken(company.getId(), "JAVAGAS_@123#"))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }
}

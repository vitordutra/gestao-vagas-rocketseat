package br.com.vitordutra.gestao_vagas.modules.company.useCases;

import br.com.vitordutra.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.vitordutra.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        // Verify if the user exists
        // If not, throw an exception
        var company = this.companyRepository
                .findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(
                        () -> {
                            return new UsernameNotFoundException("Company not found");
                        }
                );
        // Verify if the password is the same as the one in the database
        // If they're not the same: error
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException("Invalid username or password");
        }
        // If they're the same: generate a token
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);
        return token;
        
    }
}

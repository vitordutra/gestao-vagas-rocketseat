package br.com.vitordutra.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Configuring Spring Security
@Configuration
public class SecurityConfig {
   
    // @Bean explanation:
    // To spring security to understand that we need to overwrite the default configuration, 
    // that is managed by the Spring Framework. 
    // We need to create a bean of type SecurityFilterChain.
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
    
}

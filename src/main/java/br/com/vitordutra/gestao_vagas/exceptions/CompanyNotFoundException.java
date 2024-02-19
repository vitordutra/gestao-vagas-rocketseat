package br.com.vitordutra.gestao_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Company not found");
    }
}

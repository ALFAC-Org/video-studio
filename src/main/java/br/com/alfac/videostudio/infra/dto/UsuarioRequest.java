package br.com.alfac.videostudio.infra.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UsuarioRequest {

    @Schema(example = "John Doe")
    @NotEmpty(message = "Nome é obrigatório.")
    private String nome;

    @Email
    @Schema(example = "teste@teste.com")
    @NotEmpty(message = "Email é obrigatório.")
    private String email;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
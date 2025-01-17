package br.com.alfac.videostudio.infra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @Email(message = "Email inválido.")
    @NotEmpty(message = "Email é obrigatório.")
    private String email;

    @Size(min = 8, message = "Password deve ter no mínimo 8 caracteres.")
    @NotBlank(message = "Password é obrigatório.")
    private String password;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
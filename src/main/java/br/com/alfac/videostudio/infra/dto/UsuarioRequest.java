package br.com.alfac.videostudio.infra.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class UsuarioRequest {

    @Schema(example = "John Doe")
    @NotEmpty(message = "Name é obrigatório.")
    private String name;

    @Email
    @Schema(example = "teste@teste.com")
    @NotEmpty(message = "Email é obrigatório.")
    private String email;

    @Schema(example = "Teste@123")
    @Size(min = 8, message = "Password deve ter no mínimo 8 caracteres.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password deve conter no mínimo um número, um caractere especial, letras maiúsculas e minúsculas.")
    @NotBlank(message = "Password é obrigatório.")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
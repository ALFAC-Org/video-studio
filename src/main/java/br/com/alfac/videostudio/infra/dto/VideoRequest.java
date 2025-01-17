package br.com.alfac.videostudio.infra.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public class VideoRequest {

    @Schema(example = "Vídeo XYZ")
    @NotEmpty(message = "Nome é obrigatório.")
    private String nome;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

}
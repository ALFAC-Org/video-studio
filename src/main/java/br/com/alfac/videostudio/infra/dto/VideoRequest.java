package br.com.alfac.videostudio.infra.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class VideoRequest {

    @Schema(example = "Vídeo XYZ")
    @NotEmpty(message = "Nome é obrigatório.")
    private String nome;

   private MultipartFile file;

    public VideoRequest() {
    }

    public VideoRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
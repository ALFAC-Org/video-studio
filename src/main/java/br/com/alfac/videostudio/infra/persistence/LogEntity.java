package br.com.alfac.videostudio.infra.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class LogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer usuarioId;

    @NotBlank(message = "Recurso é obrigatório")
    private String resource;

    @NotBlank(message = "Data e hora são obrigatórios")
    private LocalDateTime dataCriacao;

    public LogEntity() {
    }

    public LogEntity(Integer usuarioId, String resource, LocalDateTime dataCriacao) {
        this.usuarioId = usuarioId;
        this.resource = resource;
        this.dataCriacao = dataCriacao;
    }

    // Getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}
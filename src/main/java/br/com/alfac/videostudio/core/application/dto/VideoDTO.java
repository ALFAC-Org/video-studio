package br.com.alfac.videostudio.core.application.dto;

import java.util.UUID;

import br.com.alfac.videostudio.core.domain.StatusVideo;

public class VideoDTO {
    private String nome;
    private StatusVideo status;
    private Long id;
    private UUID uuid;

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusVideo getStatus() {
        return this.status;
    }

    public void setStatus(StatusVideo status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuId(final UUID uuid) {
        this.uuid = uuid;
    }
}
package br.com.alfac.videostudio.core.domain;

import java.util.UUID;

public class Video  {
    private UUID uuid;
    private Long id;
    private StatusVideo status;
    private String nome;

    public StatusVideo getStatus() {
        return status;
    }
    
    public void setStatus(StatusVideo status) {
        this.status = status;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
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

    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }
}
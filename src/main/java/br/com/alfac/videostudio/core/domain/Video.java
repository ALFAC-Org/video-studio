package br.com.alfac.videostudio.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Video  {
    private UUID uuid;
    private Long id;
    private Long usuarioId;
    private StatusVideo status;
    private String nome;
    private LocalDateTime dataCadastro;

    public Video(Long usuarioId, String nome){
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.status = StatusVideo.PENDENTE;
        this.dataCadastro = LocalDateTime.now(); 
    }

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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
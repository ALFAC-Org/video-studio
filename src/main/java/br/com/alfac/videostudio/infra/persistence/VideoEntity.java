package br.com.alfac.videostudio.infra.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

import br.com.alfac.videostudio.core.domain.StatusVideo;

@Entity
@Table(name = "video")
public class VideoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome do vídeo é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "Status do vídeo é obrigatório")
    private StatusVideo status;

    @Column(name = "usuario_id")
    @NotNull(message = "usuarioId é obrigatório")
    private Long usuarioId;

    private UUID uuid;

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

    public StatusVideo getStatus() {
        return status;
    }

    public void setStatus(StatusVideo status) {
        this.status = status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

}
package br.com.alfac.videostudio.core.application.dto;

import java.util.UUID;

public class UsuarioDTO {
    private Long id;
    private UUID uuid;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
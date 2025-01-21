package br.com.alfac.videostudio.core.domain;

import java.io.Serializable;
import java.util.UUID;

public record UsuarioLogado(Long id, UUID uuid, String nome, String email) implements Serializable {
}

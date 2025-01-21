package br.com.alfac.videostudio.utils;

import java.util.UUID;

import br.com.alfac.videostudio.core.application.dto.UsuarioDTO;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.domain.UsuarioLogado;
import br.com.alfac.videostudio.infra.dto.UsuarioRequest;
import br.com.alfac.videostudio.infra.persistence.UsuarioEntity;

public abstract class UsuarioHelper {
    
    public static UsuarioLogado criarUsuarioLogado() {
        Long id = 1L;
        UUID uuid = UUID.randomUUID();
        String nome = "John Doe"; 
        String email = "john.doe@alfac.com";

        return new UsuarioLogado(id, uuid, nome, email);
    }
    
    public static UsuarioRequest criarUsuarioRequest() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();

        usuarioRequest.setName("John Doe");
        usuarioRequest.setEmail("john.doe@alfac.com");

        return usuarioRequest;
    }

    public static UsuarioDTO criarUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setName(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());

        return usuarioDTO;
    }

    public static UsuarioDTO criarUsuarioDTO(String nome, String email) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setName(nome != null && !nome.isEmpty() ? nome : "John Doe");

        return usuarioDTO;
    }

    public static Usuario criarUsuario() {
        return criarUsuario(null, null);
    }

    public static Usuario criarUsuario(String nome, String email) {
        Usuario usuario = new Usuario();
        long id = 123;

        usuario.setId(id);
        usuario.setUuid(UUID.randomUUID());
        usuario.setNome(nome != null && !nome.isEmpty() ? nome : "John Doe");
        usuario.setEmail(email != null && !email.isEmpty() ? nome : "john.doe@alfac.com");

        return usuario;
    }

    
    public static UsuarioEntity criarUsuarioEntity(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setUuid(usuario.getUuid());

        return usuarioEntity;
    }

}

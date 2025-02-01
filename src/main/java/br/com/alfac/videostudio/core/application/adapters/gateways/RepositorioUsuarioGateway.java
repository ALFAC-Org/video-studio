package br.com.alfac.videostudio.core.application.adapters.gateways;

import java.util.Optional;

import br.com.alfac.videostudio.core.domain.Usuario;

public interface RepositorioUsuarioGateway {

    Optional<Usuario> consultarUsuarioPorEmail(String email);
    boolean emailJaCadastrado(String email);

    Usuario cadastrarUsuario(Usuario usuario);
    Optional<Usuario> consultarPorId(Long usuarioID);

}

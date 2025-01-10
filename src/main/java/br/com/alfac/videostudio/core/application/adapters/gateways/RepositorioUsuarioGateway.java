package br.com.alfac.videostudio.core.application.adapters.gateways;

import java.util.Optional;

import br.com.alfac.videostudio.core.domain.Usuario;

public interface RepositorioUsuarioGateway {

    Optional<Usuario> consultarUsuarioPorUsername(String username);

    Usuario cadastrarUsuario(Usuario usuario);

}

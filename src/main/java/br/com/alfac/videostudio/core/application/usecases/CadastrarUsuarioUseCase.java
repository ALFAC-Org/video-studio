package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioUsuarioGateway;
import br.com.alfac.videostudio.core.application.dto.UsuarioDTO;
import br.com.alfac.videostudio.core.domain.Usuario;

public class CadastrarUsuarioUseCase {

    private final RepositorioUsuarioGateway usuarioRepository;

    public CadastrarUsuarioUseCase(final RepositorioUsuarioGateway usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario execute(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setUsername(usuarioDTO.getUsername());

        return usuarioRepository.cadastrarUsuario(usuario);
    }
}

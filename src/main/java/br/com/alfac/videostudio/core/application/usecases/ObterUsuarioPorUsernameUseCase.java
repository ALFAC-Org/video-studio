package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioUsuarioGateway;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.core.exception.usuario.UsuarioError;

public class ObterUsuarioPorUsernameUseCase {

    private final RepositorioUsuarioGateway usuarioRepository;

    public ObterUsuarioPorUsernameUseCase(final RepositorioUsuarioGateway usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario execute(String username) throws VideoStudioException {

        return usuarioRepository.consultarUsuarioPorEmail(username)
                .orElseThrow(() -> new VideoStudioException(UsuarioError.USUARIO_JA_CADASTRADO));
    }

}

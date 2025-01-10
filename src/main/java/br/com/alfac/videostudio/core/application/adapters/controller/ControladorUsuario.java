package br.com.alfac.videostudio.core.application.adapters.controller;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioUsuarioGateway;
import br.com.alfac.videostudio.core.application.adapters.presenter.UsuarioPresenter;
import br.com.alfac.videostudio.core.application.dto.UsuarioDTO;
import br.com.alfac.videostudio.core.application.usecases.CadastrarUsuarioUseCase;
import br.com.alfac.videostudio.core.domain.Usuario;

public class ControladorUsuario {

    private final RepositorioUsuarioGateway repositorioUsuarioGateway;

    public ControladorUsuario(final RepositorioUsuarioGateway repositorioUsuarioGateway) {
        this.repositorioUsuarioGateway = repositorioUsuarioGateway;
    }

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuario) {
        CadastrarUsuarioUseCase cadastrarUsuarioUseCase = new CadastrarUsuarioUseCase(this.repositorioUsuarioGateway);
        Usuario usuarioCadastrado = cadastrarUsuarioUseCase.execute(usuario);
        return UsuarioPresenter.mapearParaUsuarioDTO(usuarioCadastrado);
    }
    
}

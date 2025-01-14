package br.com.alfac.videostudio.core.application.adapters.controller;

import br.com.alfac.videostudio.core.application.adapters.presenter.UsuarioPresenter;
import br.com.alfac.videostudio.core.application.dto.UsuarioDTO;
import br.com.alfac.videostudio.core.application.usecases.CadastrarUsuarioUseCase;
import br.com.alfac.videostudio.core.domain.Usuario;

public class ControladorUsuario {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    public ControladorUsuario(final CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
    }

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuario) {
        Usuario usuarioCadastrado = cadastrarUsuarioUseCase.execute(usuario);
        return UsuarioPresenter.mapearParaUsuarioDTO(usuarioCadastrado);
    }
}

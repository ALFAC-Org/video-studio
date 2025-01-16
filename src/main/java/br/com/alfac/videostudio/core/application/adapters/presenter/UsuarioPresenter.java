package br.com.alfac.videostudio.core.application.adapters.presenter;

import br.com.alfac.videostudio.core.application.dto.UsuarioDTO;
import br.com.alfac.videostudio.core.domain.Usuario;

public final class UsuarioPresenter {

    private UsuarioPresenter() {
    }

    public static UsuarioDTO mapearParaUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setName(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setUuid(usuario.getUuid());
        return usuarioDTO;
    }

}

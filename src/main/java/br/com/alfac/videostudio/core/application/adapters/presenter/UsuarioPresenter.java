package br.com.alfac.videostudio.core.application.adapters.presenter;

import br.com.alfac.videostudio.core.application.dto.UsuarioDTO;
import br.com.alfac.videostudio.core.domain.Usuario;

public final class UsuarioPresenter {

    private UsuarioPresenter() {
    }

    public static UsuarioDTO mapearParaUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUuId(usuario.getUuid());
        return usuarioDTO;
    }

}

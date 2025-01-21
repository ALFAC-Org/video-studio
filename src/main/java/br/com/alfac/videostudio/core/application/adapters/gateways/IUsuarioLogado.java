package br.com.alfac.videostudio.core.application.adapters.gateways;

import br.com.alfac.videostudio.core.domain.UsuarioLogado;

public interface IUsuarioLogado {
    void setUsuarioLogado(UsuarioLogado usuarioLogado);

    UsuarioLogado getUsuarioLogado();
}

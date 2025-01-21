package br.com.alfac.videostudio.infra.interceptor;

import br.com.alfac.videostudio.core.application.adapters.gateways.IUsuarioLogado;
import br.com.alfac.videostudio.core.domain.UsuarioLogado;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class UsuarioLogadoImpl implements IUsuarioLogado {

    private UsuarioLogado usuarioLogado;

    @Override
    public UsuarioLogado getUsuarioLogado() {
        return usuarioLogado;
    }

    @Override
    public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}

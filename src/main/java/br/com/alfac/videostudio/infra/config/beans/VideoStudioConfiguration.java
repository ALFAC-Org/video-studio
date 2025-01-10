package br.com.alfac.videostudio.infra.config.beans;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorUsuario;
import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideo;
import br.com.alfac.videostudio.infra.gateways.RepositorioUsuarioGatewayImpl;
import br.com.alfac.videostudio.infra.gateways.RepositorioVideoGatewayImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoStudioConfiguration {

    @Bean
    public ControladorVideo controladorVideo(final RepositorioVideoGatewayImpl repositorioVideoGatewayImpl) {
        return new ControladorVideo(repositorioVideoGatewayImpl);
    }

    @Bean
    public ControladorUsuario controladorUsuario(final RepositorioUsuarioGatewayImpl repositorioUsuarioGatewayImpl) {
        return new ControladorUsuario(repositorioUsuarioGatewayImpl);
    }
}

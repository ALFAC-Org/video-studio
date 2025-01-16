package br.com.alfac.videostudio.infra.config.beans;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorUsuario;
import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideo;
import br.com.alfac.videostudio.core.application.usecases.CadastrarUsuarioUseCase;
import br.com.alfac.videostudio.core.application.usecases.ListarVideosUseCase;
import br.com.alfac.videostudio.core.application.usecases.UploadVideoUseCase;
import br.com.alfac.videostudio.infra.gateways.RepositorioUsuarioGatewayImpl;
import br.com.alfac.videostudio.infra.gateways.RepositorioVideoGatewayImpl;

import br.com.alfac.videostudio.infra.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class VideoStudioConfiguration {

    @Bean
    public ControladorVideo controladorVideo(final RepositorioVideoGatewayImpl repositorioVideoGatewayImpl) {
        ListarVideosUseCase listarVideosUseCase = new ListarVideosUseCase(repositorioVideoGatewayImpl);
        UploadVideoUseCase uploadVideoUseCase = new UploadVideoUseCase(repositorioVideoGatewayImpl);

        return new ControladorVideo(listarVideosUseCase, uploadVideoUseCase);
    }

    @Bean
    public ControladorUsuario controladorUsuario(final RepositorioUsuarioGatewayImpl repositorioUsuarioGatewayImpl, PasswordEncoder passwordEncoder) {
        CadastrarUsuarioUseCase cadastrarUsuarioUseCase = new CadastrarUsuarioUseCase(repositorioUsuarioGatewayImpl, passwordEncoder);

        return new ControladorUsuario(cadastrarUsuarioUseCase);
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }
}

package br.com.alfac.videostudio.infra.config.beans;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorUsuario;
import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideo;
import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import br.com.alfac.videostudio.core.application.usecases.*;
import br.com.alfac.videostudio.infra.gateways.RepositorioUsuarioGatewayImpl;
import br.com.alfac.videostudio.infra.gateways.RepositorioVideoGatewayImpl;

import br.com.alfac.videostudio.infra.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class VideoStudioConfiguration {

    @Value("${s3.bucket-file-processed}")
    private String bucketArquivoProcessado;

    @Bean
    public ControladorVideo controladorVideo(final RepositorioVideoGatewayImpl repositorioVideoGatewayImpl, final BucketGateway bucketGateway) {
        ListarVideosUseCase listarVideosUseCase = new ListarVideosUseCase(repositorioVideoGatewayImpl);
        UploadVideoUseCase uploadVideoUseCase = new UploadVideoUseCase(repositorioVideoGatewayImpl);
        DownloadVideoUseCase downloadVideoUseCase = new DownloadVideoUseCase(repositorioVideoGatewayImpl, bucketGateway, bucketArquivoProcessado);
        return new ControladorVideo(listarVideosUseCase, uploadVideoUseCase, downloadVideoUseCase);
    }

    @Bean
    public ControladorUsuario controladorUsuario(final RepositorioUsuarioGatewayImpl repositorioUsuarioGatewayImpl, PasswordEncoder passwordEncoder) {
        CadastrarUsuarioUseCase cadastrarUsuarioUseCase = new CadastrarUsuarioUseCase(repositorioUsuarioGatewayImpl, passwordEncoder);

        return new ControladorUsuario(cadastrarUsuarioUseCase);
    }

    @Bean
    public ObterUsuarioPorUsernameUseCase obterUsuarioPorUsername(final RepositorioUsuarioGatewayImpl repositorioUsuarioGatewayImpl) {
        return new ObterUsuarioPorUsernameUseCase(repositorioUsuarioGatewayImpl);
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }
}

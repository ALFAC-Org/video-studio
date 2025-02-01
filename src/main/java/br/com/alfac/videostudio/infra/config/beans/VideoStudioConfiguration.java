package br.com.alfac.videostudio.infra.config.beans;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorUsuario;
import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideo;
import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideoProcessado;
import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.QueueGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioUsuarioGateway;
import br.com.alfac.videostudio.core.application.usecases.*;
import br.com.alfac.videostudio.infra.gateways.RepositorioUsuarioGatewayImpl;
import br.com.alfac.videostudio.infra.gateways.RepositorioVideoGatewayImpl;

import br.com.alfac.videostudio.infra.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class VideoStudioConfiguration {

    @Value("${s3.queue-video-to-process}")
    private String queueVideoParaProcessar;

    @Value("${s3.queue-notification-error-processing}")
    private String queueNotificacaoErroProcessamento;

    @Value("${s3.bucket-video-to-process}")
    private String bucketVideoParaProcessar;

    @Value("${s3.bucket-file-processed}")
    private String bucketArquivoProcessado;

    @Value("${cloud.aws.sns.topic.arn.envia.email}")
    private String topicArn;

    @Bean
    public ControladorVideo controladorVideo(final RepositorioVideoGatewayImpl repositorioVideoGatewayImpl,
            final BucketGateway bucketGateway, final QueueGateway queueGateway) {
        ListarVideosUseCase listarVideosUseCase = new ListarVideosUseCase(repositorioVideoGatewayImpl);
        UploadVideoUseCase uploadVideoUseCase = new UploadVideoUseCase(repositorioVideoGatewayImpl, bucketGateway,
                bucketVideoParaProcessar, queueGateway, queueVideoParaProcessar);
        DownloadVideoUseCase downloadVideoUseCase = new DownloadVideoUseCase(repositorioVideoGatewayImpl, bucketGateway,
                bucketArquivoProcessado);
        return new ControladorVideo(listarVideosUseCase, uploadVideoUseCase, downloadVideoUseCase);
    }

    @Bean
    public ControladorUsuario controladorUsuario(final RepositorioUsuarioGatewayImpl repositorioUsuarioGatewayImpl,
            PasswordEncoder passwordEncoder) {
        CadastrarUsuarioUseCase cadastrarUsuarioUseCase = new CadastrarUsuarioUseCase(repositorioUsuarioGatewayImpl,
                passwordEncoder);

        return new ControladorUsuario(cadastrarUsuarioUseCase);
    }

    @Bean
    public ObterUsuarioPorUsernameUseCase obterUsuarioPorUsername(
            final RepositorioUsuarioGatewayImpl repositorioUsuarioGatewayImpl) {
        return new ObterUsuarioPorUsernameUseCase(repositorioUsuarioGatewayImpl);
    }

    @Bean
    public ControladorVideoProcessado controladorVideoProcessado(final RepositorioVideoGatewayImpl repositorioVideoGatewayImpl, final SnsClient snsClient, final RepositorioUsuarioGateway usuarioGateway) {
        AtualizarStatusVideoUseCase atualizarStatusVideoUseCase = new AtualizarStatusVideoUseCase(
                repositorioVideoGatewayImpl);
        ErroProcessamentoVideoUseCase erroProcessamentoVideoUseCase = new ErroProcessamentoVideoUseCase(repositorioVideoGatewayImpl, snsClient, queueNotificacaoErroProcessamento, topicArn, usuarioGateway);
        return new ControladorVideoProcessado(atualizarStatusVideoUseCase, erroProcessamentoVideoUseCase);
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }
}

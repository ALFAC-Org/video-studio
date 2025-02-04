package br.com.alfac.videostudio.unit.core.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioUsuarioGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.usecases.ErroProcessamentoVideoUseCase;
import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.domain.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ErroProcessamentoVideoUseCaseTest {

    private RepositorioVideoGateway videoRepository;
    private RepositorioUsuarioGateway usuarioGateway;
    private SnsClient snsClient;
    private ErroProcessamentoVideoUseCase erroProcessamentoVideoUseCase;

    @BeforeEach
    public void setUp() {
        videoRepository = mock(RepositorioVideoGateway.class);
        usuarioGateway = mock(RepositorioUsuarioGateway.class);
        snsClient = mock(SnsClient.class);
        erroProcessamentoVideoUseCase = new ErroProcessamentoVideoUseCase(videoRepository, snsClient, "queueNotificacaoErroProcessamento", "topicArn", usuarioGateway);
    }

    @Test
    public void testExecuteVideoFound() {
        UUID uuid = UUID.randomUUID();
        Video video = new Video(1L, "videoTitle");
        video.setUuid(uuid);
        video.setUsuarioId(1L);
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");

        when(videoRepository.consultarVideoPorUuId(uuid)).thenReturn(Optional.of(video));
        when(usuarioGateway.consultarPorId(video.getUsuarioId())).thenReturn(Optional.of(usuario));

        erroProcessamentoVideoUseCase.execute(uuid);

        verify(videoRepository).atualizarStatus(video.getId(), StatusVideo.ERRO);
        ArgumentCaptor<PublishRequest> publishRequestCaptor = ArgumentCaptor.forClass(PublishRequest.class);
        verify(snsClient).publish(publishRequestCaptor.capture());

        PublishRequest publishRequest = publishRequestCaptor.getValue();
        assertEquals("Erro no processamento do vídeo com UUID: " + uuid, publishRequest.message());
    }

    @Test
    public void testExecuteVideoNotFound() {
        UUID uuid = UUID.randomUUID();

        when(videoRepository.consultarVideoPorUuId(uuid)).thenReturn(Optional.empty());

        erroProcessamentoVideoUseCase.execute(uuid);

        verify(videoRepository, never()).atualizarStatus(anyLong(), any(StatusVideo.class));
        verify(snsClient, never()).publish(any(PublishRequest.class));
    }

    @Test
    public void testExecuteUsuarioNotFound() {
        UUID uuid = UUID.randomUUID();
        Video video = new Video(1L, "videoTitle");
        video.setUuid(uuid);
        video.setUsuarioId(1L);

        when(videoRepository.consultarVideoPorUuId(uuid)).thenReturn(Optional.of(video));
        when(usuarioGateway.consultarPorId(video.getUsuarioId())).thenReturn(Optional.empty());

        erroProcessamentoVideoUseCase.execute(uuid);

        verify(videoRepository, never()).atualizarStatus(anyLong(), any(StatusVideo.class));
        verify(snsClient, never()).publish(any(PublishRequest.class));
    }
}

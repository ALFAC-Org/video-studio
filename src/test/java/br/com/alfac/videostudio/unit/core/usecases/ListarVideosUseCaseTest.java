package br.com.alfac.videostudio.unit.core.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.usecases.ListarVideosUseCase;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.utils.VideoHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListarVideosUseCaseTest {

    @Mock
    private RepositorioVideoGateway repositorioVideoGateway;

    @InjectMocks
    private ListarVideosUseCase listarVideosUseCase;

    @Test
    void deveListarVideos() throws VideoStudioException {
        //Arrange
        Long usuarioLogadoId = 1L;
        Video video1 = VideoHelper.criarVideo();
        Video video2 = VideoHelper.criarVideo();
        List<Video> videos = Arrays.asList(video1, video2);

        when(repositorioVideoGateway.listarVideosUsuario(any(Long.class))).thenReturn(videos);

        //Act
        List<Video> itensObtidos = listarVideosUseCase.execute(usuarioLogadoId);

        //Assert
        assertThat(itensObtidos)
            .hasSize(2)
            .containsExactlyInAnyOrder(video1, video2);

        verify(repositorioVideoGateway, times(1)).listarVideosUsuario(any(Long.class));
    }

}

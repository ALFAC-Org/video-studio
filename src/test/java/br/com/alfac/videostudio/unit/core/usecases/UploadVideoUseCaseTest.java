package br.com.alfac.videostudio.unit.core.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.usecases.UploadVideoUseCase;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.utils.VideoHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadVideoUseCaseTest {

    @Mock
    private RepositorioVideoGateway repositorioVideoGateway;

    @InjectMocks
    private UploadVideoUseCase uploadVideoUseCase;

    @Test
    void deveRegistrarUploadDeUmVideo() {
        // Arrange
        Long usuarioLogadoId = 1L;
        VideoDTO videoDTO = VideoHelper.criarVideoDTO();
        Video video = VideoHelper.criarVideo();
        
        when(repositorioVideoGateway.registrarUploadVideo(any(Video.class))).thenReturn(video);

        //Act
        Video videoRetornado = uploadVideoUseCase.execute(usuarioLogadoId, videoDTO);

        //Assert
        assertThat(videoRetornado).isInstanceOf(Video.class).isNotNull();
        assertThat(videoRetornado.getNome()).isEqualTo(video.getNome());
        assertThat(videoRetornado.getStatus()).isEqualTo(video.getStatus());
        assertThat(videoRetornado.getUuid()).isNotNull();

        verify(repositorioVideoGateway, times(1)).registrarUploadVideo(any(Video.class));
    }

    @Test
    void deveGerarExcecaoQuandoVideoDTOIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> uploadVideoUseCase.execute(1L, null));
    }

}

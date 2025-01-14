package br.com.alfac.videostudio.unit.core.adapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mockConstruction;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideo;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.usecases.ListarVideosUseCase;
import br.com.alfac.videostudio.core.application.usecases.UploadVideoUseCase;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.utils.VideoHelper;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControladorVideoTest {
    @Mock
    private ListarVideosUseCase listarVideosUseCase;

    @Mock
    private UploadVideoUseCase uploadVideoUseCase;

    @InjectMocks
    private ControladorVideo controladorVideo;

    @Test
    void devePermitirListarVideosUsuario() throws Exception {
        // Arrange
        when(listarVideosUseCase.execute()).thenReturn(Arrays.asList(VideoHelper.criarVideo()));

        // Act
        List<VideoDTO> videos = controladorVideo.listarVideosUsuario();

        // Assert
        assertThat(videos).isNotNull().isNotEmpty();
        assertThat(videos).hasSize(1);
        assertThat(videos.get(0)).isInstanceOf(VideoDTO.class);
    }

    @Test
    void devePermitirUploadVideo() throws VideoStudioException {
        // Arrange
        when(uploadVideoUseCase.execute(any(VideoDTO.class))).thenReturn(VideoHelper.criarVideo());

        // Act
        VideoDTO videoDTO = controladorVideo.uploadVideo(VideoHelper.criarVideoDTO());

        // Assert
        assertThat(videoDTO).isNotNull().isInstanceOf(VideoDTO.class);
    }
}
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
    private RepositorioVideoGateway repositorioVideoGateway;

    private ControladorVideo controladorVideo;
 
    @BeforeEach
    void setUp() {
        controladorVideo = new ControladorVideo(repositorioVideoGateway);
    }

    @Test
    void devePermitirListarItens() throws Exception {
        //Arrange
        Video video1 = VideoHelper.criarVideo();
        Video video2 = VideoHelper.criarVideo();
        List<Video> videoList = Arrays.asList(video1, video2);

        VideoDTO item1DTO = VideoHelper.criarVideoDTO(video1);
        VideoDTO item2DTO = VideoHelper.criarVideoDTO(video2);

        try (MockedConstruction<ListarVideosUseCase> mockedListarVideosUseCase = 
            mockConstruction(ListarVideosUseCase.class, (mock, context) -> {
                when(mock.execute()).thenReturn(videoList);
            })) {

            //Act
            List<VideoDTO> videosObtidos = controladorVideo.listarVideosUsuario();

            //Assert
            assertThat(videosObtidos)
                .hasSize(2)
                .containsExactlyInAnyOrder(item1DTO, item2DTO);
        }
    }

    @Test
    void devePermitirCadastrarItem() throws VideoStudioException {
        // Arrange
        Video video = VideoHelper.criarVideo();
        video.setId(1L);
        VideoDTO videoDTO = VideoHelper.criarVideoDTO(video);

        try (MockedConstruction<UploadVideoUseCase> mockedUploadVideoUseCase = 
            mockConstruction(UploadVideoUseCase.class, (mock, context) -> {
                when(mock.execute(any(VideoDTO.class))).thenReturn(video);
            })) {

            // Act
            var videoSalvo = controladorVideo.uploadVideo(videoDTO);

            // Assert
            assertThat(videoSalvo)
                .isInstanceOf(VideoDTO.class)
                .isNotNull()
                .isEqualTo(videoDTO);

            assertThat(videoSalvo).extracting(VideoDTO::getId).isEqualTo(video.getId());
            assertThat(videoSalvo).extracting(VideoDTO::getNome).isEqualTo(video.getNome());
            assertThat(videoSalvo).extracting(VideoDTO::getStatus).isEqualTo(video.getStatus());
        }
    }

}
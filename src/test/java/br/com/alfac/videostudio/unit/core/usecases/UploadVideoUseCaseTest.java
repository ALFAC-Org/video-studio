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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadVideoUseCaseTest {

    @Mock
    private RepositorioVideoGateway repositorioVideoGateway;

    @InjectMocks
    private UploadVideoUseCase uploadVideoUseCase;

    @Test
    void deveDevolverUmCliente() {
        // Arrange
        VideoDTO videoDTO = VideoHelper.criarVideoDTO();
        Video video = VideoHelper.criarVideo();
        when(repositorioVideoGateway.registrarUploadVideo(any(Video.class))).thenReturn(video);

        // Act
        Video execute = uploadVideoUseCase.execute(videoDTO);

        // Assert
        var clienteRetornado = assertThat(execute)
                .isInstanceOf(Video.class);

        clienteRetornado
                .extracting(Video::getNome)
                .isEqualTo(video.getNome());
        clienteRetornado
                .extracting(Video::getStatus)
                .isEqualTo(video.getStatus());
    }

    @Test
    void execute() {
        // Arrange
        when(repositorioVideoGateway.registrarUploadVideo(any(Video.class))).thenReturn(new Video());

        // Act
        Video execute = uploadVideoUseCase.execute(new VideoDTO());

        // Assert
        assertNotNull(execute);
    }

    @Test
    void shouldThrowExceptionWhenClienteDTOIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> uploadVideoUseCase.execute(null));
    }

}

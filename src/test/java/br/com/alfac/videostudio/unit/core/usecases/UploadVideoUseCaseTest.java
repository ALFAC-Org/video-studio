package br.com.alfac.videostudio.unit.core.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.usecases.UploadVideoUseCase;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.utils.VideoHelper;
import io.cucumber.java.Before;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadVideoUseCaseTest {

    @Mock
    private RepositorioVideoGateway repositorioVideoGateway;
    
    @Mock
    private BucketGateway bucketGateway;

    private UploadVideoUseCase uploadVideoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        String bucketNameMock = "mocked-bucket-name";
        uploadVideoUseCase = new UploadVideoUseCase(repositorioVideoGateway, bucketGateway, bucketNameMock);
    }

    @Test
    void deveRegistrarUploadDeUmVideo() {
        // Arrange
        Long usuarioLogadoId = 1L;
        byte[] file = null;
        VideoDTO videoDTO = VideoHelper.criarVideoDTO();
        Video video = VideoHelper.criarVideo();
        
        when(repositorioVideoGateway.registrarUploadVideo(any(Video.class))).thenReturn(video);
        doNothing().when(bucketGateway).uploadFile(anyString(), any(), anyString());

        //Act
        Video videoRetornado = uploadVideoUseCase.execute(usuarioLogadoId, videoDTO, file);

        //Assert
        assertThat(videoRetornado).isInstanceOf(Video.class).isNotNull();
        assertThat(videoRetornado.getNome()).isEqualTo(video.getNome());
        assertThat(videoRetornado.getStatus()).isEqualTo(video.getStatus());
        assertThat(videoRetornado.getUuid()).isNotNull();

        verify(repositorioVideoGateway, times(1)).registrarUploadVideo(any(Video.class));
        verify(bucketGateway, times(1)).uploadFile(anyString(), any(), anyString());
    }

    @Test
    void deveGerarExcecaoQuandoVideoDTOIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> uploadVideoUseCase.execute(1L, null, null));
    }

}

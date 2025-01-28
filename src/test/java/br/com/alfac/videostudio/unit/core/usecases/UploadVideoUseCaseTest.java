package br.com.alfac.videostudio.unit.core.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.QueueGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.usecases.CadastrarUsuarioUseCase;
import br.com.alfac.videostudio.core.application.usecases.UploadVideoUseCase;
import br.com.alfac.videostudio.core.application.util.FileValidator;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.utils.VideoHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadVideoUseCaseTest {

    @Mock
    private RepositorioVideoGateway repositorioVideoGateway;
    
    @Mock
    private QueueGateway queueGateway;
    
    @Mock
    private BucketGateway bucketGateway;

    private UploadVideoUseCase uploadVideoUseCase;

    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.openMocks(this);
        String queueNameMock = "mocked-queue-name";
        String bucketNameMock = "mocked-bucket-name";
        uploadVideoUseCase = new UploadVideoUseCase(repositorioVideoGateway, bucketGateway, bucketNameMock, queueGateway, queueNameMock);
    }

    @Test
    void deveRegistrarUploadDeUmVideo() throws VideoStudioException {
        //Arrange
        Long usuarioLogadoId = 1L;
        byte[] file = "conteudo".getBytes();
        VideoDTO videoDTO = VideoHelper.criarVideoDTO();
        Video video = VideoHelper.criarVideo();

        mockStatic(FileValidator.class);
        when(FileValidator.isMp4File(any(byte[].class))).thenReturn(true);

        when(repositorioVideoGateway.registrarUploadVideo(any(Video.class))).thenReturn(video);
        doNothing().when(bucketGateway).uploadFile(anyString(), any(), anyString());
        doNothing().when(queueGateway).sendMessage(anyString(), anyString());

        //Act
        Video videoRetornado = uploadVideoUseCase.execute(usuarioLogadoId, videoDTO, file);

        //Assert
        assertThat(videoRetornado).isInstanceOf(Video.class).isNotNull();
        assertThat(videoRetornado.getNome()).isEqualTo(video.getNome());
        assertThat(videoRetornado.getStatus()).isEqualTo(video.getStatus());
        assertThat(videoRetornado.getUuid()).isNotNull();

        verify(repositorioVideoGateway, times(1)).registrarUploadVideo(any(Video.class));
        verify(bucketGateway, times(1)).uploadFile(anyString(), any(), anyString());
        verify(queueGateway, times(1)).sendMessage(anyString(), anyString());
    }

    @Test
    void deveGerarExcecaoQuandoVideoDTOIsNull() {
        //Arrange
        Long usuarioLogadoId = 1L;
        byte[] file = "conteudo".getBytes();
        VideoDTO videoDTO = null;

        when(FileValidator.isMp4File(any(byte[].class))).thenReturn(true);

        //Act & Assert
        assertThrows(NullPointerException.class, () -> uploadVideoUseCase.execute(usuarioLogadoId, videoDTO, file));
    }

    @Test
    void deveGerarExcecaoQuandoFormatoArquivoInvalido() {
        //Arrange
        Long usuarioLogadoId = 1L;
        byte[] file = "conteudo".getBytes();
        VideoDTO videoDTO = VideoHelper.criarVideoDTO();

        when(FileValidator.isMp4File(any(byte[].class))).thenReturn(false);

        //Act & Assert
        assertThatThrownBy(() -> uploadVideoUseCase.execute(usuarioLogadoId, videoDTO, file))
            .isInstanceOf(VideoStudioException.class)
            .hasMessage("Formato de arquivo inválido");
    }

}

package br.com.alfac.videostudio.unit.core.usecases;

import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.utils.VideoHelper;
import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.QueueGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.usecases.UploadVideoUseCase;
import br.com.alfac.videostudio.core.application.util.FileValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
public class UploadVideoUseCaseTest {

      @Mock
      private RepositorioVideoGateway videoRepository;

      @Mock
      private QueueGateway queueGateway;

      @Mock
      private BucketGateway bucketGateway;

      @Mock
      private FileValidator fileValidator;

      @InjectMocks
      private UploadVideoUseCase uploadVideoUseCase;

      @BeforeEach
      void setUp() {
            MockitoAnnotations.openMocks(this);
            String queueNameMock = "mocked-queue-name";
            String bucketNameMock = "mocked-bucket-name";
            uploadVideoUseCase = new UploadVideoUseCase(videoRepository, bucketGateway, bucketNameMock, queueGateway,
                        queueNameMock);
      }

      @Test
      void deveRegistrarUploadDeUmVideo() throws VideoStudioException {
            // Arrange
            Long usuarioLogadoId = 1L;
            byte[] file = new byte[] {
                  0x00, 0x00, 0x00, 0x18,  // Tamanho do box 'ftyp'
                  0x66, 0x74, 0x79, 0x70,  // 'ftyp' signature
                  0x6D, 0x70, 0x34, 0x32,  // Tipo do arquivo: 'mp42'
                  0x00, 0x00, 0x00, 0x00,  // Flags
                  0x6D, 0x70, 0x34, 0x31,  // Compatibilidade: 'mp41'
                  0x6D, 0x70, 0x34, 0x32   // Compatibilidade: 'mp42'
              };
            VideoDTO videoDTO = VideoHelper.criarVideoDTO();
            Video video = VideoHelper.criarVideo();

            when(videoRepository.registrarUploadVideo(any(Video.class))).thenReturn(video);

            // Act
            Video videoRetornado = uploadVideoUseCase.execute(usuarioLogadoId, videoDTO, file);

            // Assert
            assertThat(videoRetornado).isInstanceOf(Video.class).isNotNull();
            assertThat(videoRetornado.getNome()).isEqualTo(video.getNome());
            assertThat(videoRetornado.getStatus()).isEqualTo(video.getStatus());
            assertThat(videoRetornado.getUuid()).isNotNull();

            verify(videoRepository, times(1)).registrarUploadVideo(any(Video.class));
            verify(bucketGateway, times(1)).uploadFile(anyString(), any(byte[].class), anyString());
            verify(queueGateway, times(1)).sendMessage(anyString(), anyString());
      }

      @Test
      void deveGerarExcecaoQuandoFormatoArquivoInvalido() {
            // Arrange
            Long usuarioLogadoId = 1L;
            byte[] file = "conteudo".getBytes(); // Exemplo de conteúdo de arquivo inválido
            VideoDTO videoDTO = VideoHelper.criarVideoDTO();

            // Act & Assert
            assertThrows(VideoStudioException.class, () -> uploadVideoUseCase.execute(usuarioLogadoId, videoDTO, file));
      }
}
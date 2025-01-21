package br.com.alfac.videostudio.unit.infra.gateways;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.infra.gateways.RepositorioVideoGatewayImpl;
import br.com.alfac.videostudio.infra.mapper.VideoEntityMapper;
import br.com.alfac.videostudio.infra.persistence.VideoEntity;
import br.com.alfac.videostudio.infra.persistence.VideoEntityRepository;
import br.com.alfac.videostudio.utils.VideoHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepositorioVideoGatewayImplTest {

    @Mock
    private VideoEntityRepository videoEntityRepository;

    @Mock
    private VideoEntityMapper videoEntityMapper;

    @InjectMocks
    private RepositorioVideoGatewayImpl repositorioVideoGateway;
 
    @BeforeEach
    void setUp() {
        repositorioVideoGateway = new RepositorioVideoGatewayImpl(videoEntityRepository, videoEntityMapper);
    }

    @Test
    void devePermitirConsultarVideoPorUuId() throws VideoStudioException {
        //Arrange
        UUID randomUuid = UUID.randomUUID();
        Video video = VideoHelper.criarVideo();
        video.setUuid(randomUuid);
        VideoEntity videoEntity = VideoHelper.criarVideoEntity(video);

        when(videoEntityRepository.findByUuid(any(UUID.class))).thenReturn(Optional.of(videoEntity));

        //Act
        var videoOpcional = repositorioVideoGateway.consultarVideoPorUuId(randomUuid);

        //Assert
        videoOpcional.ifPresent(videoArmazenado -> {
            assertThat(videoArmazenado.getUsuarioId())
                .isEqualTo(video.getUsuarioId());
            assertThat(videoArmazenado.getNome())
                .isEqualTo(video.getNome());
            assertThat(videoArmazenado.getStatus())
                .isEqualTo(video.getStatus());
            });

        verify(videoEntityRepository, times(1)).findByUuid(any(UUID.class));
    }

    @Test
    void devePermitirConsultarVideoPorUuIdEUsuarioId() throws VideoStudioException {
        //Arrange
        UUID randomUuid = UUID.randomUUID();
        Long usuarioLogadoId = 1L;
        Video video = VideoHelper.criarVideo();
        video.setUuid(randomUuid);
        VideoEntity videoEntity = VideoHelper.criarVideoEntity(video);

        when(videoEntityRepository.findByUuidAndUsuarioId(any(UUID.class), anyLong())).thenReturn(Optional.of(videoEntity));

        //Act
        var videoOpcional = repositorioVideoGateway.consultarVideoPorUuIdEUsuarioId(randomUuid, usuarioLogadoId);

        //Assert
        videoOpcional.ifPresent(videoArmazenado -> {
            assertThat(videoArmazenado.getUsuarioId())
                .isEqualTo(video.getUsuarioId());
            assertThat(videoArmazenado.getNome())
                .isEqualTo(video.getNome());
            assertThat(videoArmazenado.getStatus())
                .isEqualTo(video.getStatus());
            });

        verify(videoEntityRepository, times(1)).findByUuidAndUsuarioId(any(UUID.class), anyLong());
    }

    @Test
    void devePermitirListarVideosUsuario() throws VideoStudioException {
        //Arrange
        Long usuarioLogadoId = 1L;
        Video video1 = VideoHelper.criarVideo();
        Video video2 = VideoHelper.criarVideo();
        VideoEntity videoEntity1 = VideoHelper.criarVideoEntity(video1);
        VideoEntity videoEntity2 = VideoHelper.criarVideoEntity(video2);
        List<VideoEntity> videosEntity = Arrays.asList(
            videoEntity1,
            videoEntity2
        );

        when(videoEntityRepository.findByUsuarioId(anyLong())).thenReturn(videosEntity);
        when(videoEntityMapper.toDomain(videoEntity1)).thenReturn(video1);
        when(videoEntityMapper.toDomain(videoEntity2)).thenReturn(video2);

        //Act
        var videosObtidos = repositorioVideoGateway.listarVideosUsuario(usuarioLogadoId);

        //Assert
        assertThat(videosObtidos)
            .hasSize(2)
            .containsExactlyInAnyOrder(video1, video2);

        verify(videoEntityRepository, times(1)).findByUsuarioId(anyLong());
        verify(videoEntityMapper, times(2)).toDomain(any(VideoEntity.class));
    }

    @Test
    void devePermitirRegistrarUploadVideo() throws VideoStudioException {
        // Arrange
        Video video = VideoHelper.criarVideo();
        VideoEntity videoEntity = VideoHelper.criarVideoEntity(video);

        when(videoEntityRepository.save(any(VideoEntity.class))).thenReturn(videoEntity);

        // Act
        var videoSalvo = repositorioVideoGateway.registrarUploadVideo(video);

        // Assert
        assertThat(videoSalvo).isInstanceOf(Video.class).isNotNull();
        assertThat(videoSalvo).extracting(Video::getNome).isEqualTo(video.getNome());
        assertThat(videoSalvo).extracting(Video::getUsuarioId).isEqualTo(video.getUsuarioId());
        assertThat(videoSalvo).extracting(Video::getStatus).isEqualTo(video.getStatus());
        
        verify(videoEntityRepository, times(1)).save(any(VideoEntity.class));
    }

}
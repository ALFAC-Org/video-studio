package br.com.alfac.videostudio.unit.infra.gateways;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.infra.gateways.RepositorioVideoGatewayImpl;
import br.com.alfac.videostudio.infra.mapper.VideoEntityMapper;
import br.com.alfac.videostudio.infra.persistence.VideoEntity;
import br.com.alfac.videostudio.infra.persistence.VideoEntityRepository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RepositorioVideoGatewayImplTest {

    @Mock
    private VideoEntityRepository videoEntityRepository;

    @Mock
    private VideoEntityMapper videoEntityMapper;

    @InjectMocks
    private RepositorioVideoGatewayImpl repositorioVideoGateway;

    private VideoEntity videoEntity;
    private Video video;

    @BeforeEach
    void setUp() {
        videoEntity = new VideoEntity();
        videoEntity.setId(1L);
        videoEntity.setUuid(UUID.randomUUID());
        videoEntity.setNome("Nome Teste");

        video =  new Video(1L, "");
        video.setId(1L);
        video.setUuid(UUID.randomUUID());
        video.setNome("Nome Teste");
    }

    @Test
    void deveConsultarVideoPorUuId() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        video.setUuid(uuid);
        videoEntity.setUuid(uuid);

        when(videoEntityRepository.findByUuid(any(UUID.class))).thenReturn(Optional.of(videoEntity));
        when(videoEntityMapper.toDomain(videoEntity)).thenReturn(video);

        // Act
        Optional<Video> result = repositorioVideoGateway.consultarVideoPorUuId(uuid);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(video);
    }

    @Test
    void deveCadastrarVideo() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        video.setUuid(uuid);
        videoEntity.setUuid(uuid);

        when(videoEntityMapper.toEntity(video)).thenReturn(videoEntity);
        when(videoEntityRepository.save(any(VideoEntity.class))).thenReturn(videoEntity);
        when(videoEntityMapper.toDomain(videoEntity)).thenReturn(video);

        assertThat(videoEntityMapper.toEntity(video)).isEqualTo(videoEntity);
        assertThat(videoEntityRepository.save(videoEntity)).isEqualTo(videoEntity);
        assertThat(videoEntityMapper.toDomain(videoEntity)).isEqualTo(video);

        // Act
        Video result = repositorioVideoGateway.registrarUploadVideo(video);

        assertThat(result).isNotNull();

        // Assert
        assertThat(result).usingRecursiveComparison().isEqualTo(video);
    }

    @Test
    void shouldThrowExceptionWhenVideoIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> repositorioVideoGateway.registrarUploadVideo(null));
    }
}
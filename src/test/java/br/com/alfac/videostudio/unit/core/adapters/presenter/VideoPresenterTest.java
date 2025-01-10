package br.com.alfac.videostudio.unit.core.adapters.presenter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.videostudio.core.application.adapters.presenter.VideoPresenter;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.domain.Video;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class VideoPresenterTest {

    @Test
    void deveConseguirMapearParaVideoDTO() {
        // Arrange
        Video video = new Video();
        video.setId(1L);
        video.setUuid(UUID.randomUUID());
        video.setNome("Nome Teste");

        // Act
        VideoDTO videoDTO = VideoPresenter.mapearParaVideoDTO(video);

        // Assert
        assertThat(videoDTO.getId()).isEqualTo(video.getId());
        assertThat(videoDTO.getUuid()).isEqualTo(video.getUuid());
        assertThat(videoDTO.getNome()).isEqualTo(video.getNome());
    }
}

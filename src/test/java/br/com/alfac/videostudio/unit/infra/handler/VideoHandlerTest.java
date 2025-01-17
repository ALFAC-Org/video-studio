package br.com.alfac.videostudio.unit.infra.handler;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioError;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.core.exception.video.VideoError;
import br.com.alfac.videostudio.infra.handler.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideo;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.infra.handler.VideoHandler;
import br.com.alfac.videostudio.utils.VideoHelper;

class VideoHandlerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private VideoHandler videoHandler;

    @Mock
    ControladorVideo controladorVideo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(videoHandler)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Nested
    class ListarVideos {
        @Test
        void deveListarVideosUsuario() throws Exception {
            Video video = new Video(1L, "");
            video.setId(1L);
            video.setNome("Ayrton Senna");
            video.setStatus(StatusVideo.PENDENTE);

            VideoDTO videoDTO = VideoHelper.criarVideoDTO(video);
            List<VideoDTO> videoDTOList = Arrays.asList(videoDTO);

            when(controladorVideo.listarVideosUsuario(1L)).thenReturn(videoDTOList);

            mockMvc.perform(get("/api/v1/videos/johndoe")
                                    .contentType(MediaType.APPLICATION_JSON)
                            // .header("Authorization", "Bearer token"))
                    ).andExpect(status().isOk())
                    .andExpect(jsonPath("$.[0].id").value(videoDTO.getId().toString()))
                    .andExpect(jsonPath("$.[0].nome").value(videoDTO.getNome()))
                    .andExpect(jsonPath("$.[0].status").value(videoDTO.getStatus().name()));
        }

        @Test
        void deveRetornarNenhumVideoCadastrado() throws Exception {
            when(controladorVideo.listarVideosUsuario(1L)).thenThrow(new VideoStudioException(VideoError.VIDEOS_NOT_FOUND));

            mockMvc.perform(get("/api/v1/videos/johndoe")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string("Vídeos não encontrados"));
        }
    }
}
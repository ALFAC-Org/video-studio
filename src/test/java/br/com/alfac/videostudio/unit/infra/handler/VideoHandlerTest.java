package br.com.alfac.videostudio.unit.infra.handler;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.domain.UsuarioLogado;
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
import br.com.alfac.videostudio.core.application.adapters.gateways.IUsuarioLogado;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.infra.handler.VideoHandler;
import br.com.alfac.videostudio.utils.UsuarioHelper;
import br.com.alfac.videostudio.utils.VideoHelper;

class VideoHandlerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private VideoHandler videoHandler;

    @Mock
    ControladorVideo controladorVideo;

    @Mock
    IUsuarioLogado usuarioLogadoImpl;

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
            UsuarioLogado usuarioLogado = UsuarioHelper.criarUsuarioLogado();
            VideoDTO videoDTO = VideoHelper.criarVideoDTO();
            List<VideoDTO> videoDTOList = Arrays.asList(videoDTO);

            when(controladorVideo.listarVideosUsuario(anyLong())).thenReturn(videoDTOList);
            when(usuarioLogadoImpl.getUsuarioLogado()).thenReturn(usuarioLogado);

            mockMvc.perform(get("/api/v1/videos")
                                    .contentType(MediaType.APPLICATION_JSON)
                            // .header("Authorization", "Bearer token"))
                    ).andExpect(status().isOk())
                    .andExpect(jsonPath("$.[0].nome").value(videoDTO.getNome()))
                    .andExpect(jsonPath("$.[0].status").value(videoDTO.getStatus().name()));
        }

        @Test
        void deveRetornarNenhumVideoCadastrado() throws Exception {
            UsuarioLogado usuarioLogado = UsuarioHelper.criarUsuarioLogado();

            when(controladorVideo.listarVideosUsuario(anyLong())).thenThrow(new VideoStudioException(VideoError.VIDEOS_NOT_FOUND));
            when(usuarioLogadoImpl.getUsuarioLogado()).thenReturn(usuarioLogado);

            mockMvc.perform(get("/api/v1/videos")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string("Vídeos não encontrados"));
        }
    }
}
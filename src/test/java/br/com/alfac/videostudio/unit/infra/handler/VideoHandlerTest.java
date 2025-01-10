package br.com.alfac.videostudio.unit.infra.handler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideo;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.infra.config.exception.VideoStudioExceptionHandler;
import br.com.alfac.videostudio.infra.dto.VideoRequest;
import br.com.alfac.videostudio.infra.handler.VideoHandler;
import br.com.alfac.videostudio.infra.mapper.VideoMapper;
import br.com.alfac.videostudio.utils.VideoHelper;

class VideoHandlerTest {

    private MockMvc mockMvc;

    @Mock
    private ControladorVideo controladorVideoMySQL;

    @Mock
    private VideoMapper videoMapper;

    @InjectMocks
    private VideoHandler videoHandler;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(videoHandler)
                .setControllerAdvice(new VideoStudioExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirListarItens() throws Exception {
        //Arrange
        VideoDTO videoDTO = VideoHelper.criarVideoDTO();
        List<VideoDTO> videoDTOList = Arrays.asList(videoDTO);

        when(controladorVideoMySQL.listarVideosUsuario()).thenReturn(videoDTOList);
      
        //Act/Assert
        mockMvc.perform(get("/api/v1/videos")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(videoDTO.getId().toString()))
                .andExpect(jsonPath("$.[0].nome").value(videoDTO.getNome()))
                .andExpect(jsonPath("$.[0].status").value(videoDTO.getStatus().name()));

            verify(controladorVideoMySQL, times(1)).listarVideosUsuario();
    }

    @Test
    void devePermitirUploadVideo() throws Exception {
        // Arrange
        VideoRequest videoRequest = new VideoRequest();
        VideoDTO videoDTO = new VideoDTO();
        when(videoMapper.toDTO(any(VideoRequest.class))).thenReturn(videoDTO);
        when(controladorVideoMySQL.uploadVideo(any(VideoDTO.class))).thenReturn(videoDTO);

        // Act & Assert
        mockMvc.perform(post("/api/v1/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(videoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(videoDTO.getNome()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package br.com.alfac.videostudio.unit.infra.dto;

import br.com.alfac.videostudio.infra.dto.VideoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import static org.junit.jupiter.api.Assertions.*;

public class VideoRequestTest {

    @Test
    public void testConstructor() {
        VideoRequest videoRequest = new VideoRequest("Vídeo XYZ");
        assertNotNull(videoRequest);
        assertEquals("Vídeo XYZ", videoRequest.getNome());
    }

    @Test
    public void testNome() {
        VideoRequest videoRequest = new VideoRequest();
        videoRequest.setNome("Vídeo XYZ");
        assertEquals("Vídeo XYZ", videoRequest.getNome());
    }

    @Test
    public void testFile() {
        VideoRequest videoRequest = new VideoRequest();
        MockMultipartFile mockFile = new MockMultipartFile("file", "video.mp4", "video/mp4", new byte[0]);
        videoRequest.setFile(mockFile);
        assertEquals(mockFile, videoRequest.getFile());
    }

    @Test
    public void testEmptyNome() {
        VideoRequest videoRequest = new VideoRequest();
        videoRequest.setNome("");
        assertNotNull(videoRequest.getNome());
    }
}


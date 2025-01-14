package br.com.alfac.videostudio.unit;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = br.com.alfac.videostudio.VideoStudioApplication.class)
public class VideoStudioApplicationTest extends BaseTest {

    @Test
    void load() {
        System.out.println("Starting the VideoStudioApplicationTest file...");
    }

}
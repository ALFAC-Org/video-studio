package br.com.alfac.videostudio.unit;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// import br.com.alfac.videostudio.unit.infra.config.AWS.SQS.TestSQSConfig;

// @SpringBootTest(classes = {br.com.alfac.videostudio.VideoStudioApplication.class, TestSQSConfig.class})
@SpringBootTest(classes = {br.com.alfac.videostudio.VideoStudioApplication.class})
@ActiveProfiles("test")
public class VideoStudioApplicationTest extends BaseTest {

    @Test
    void load() {
        System.out.println("Starting the VideoStudioApplicationTest file...");
    }

}
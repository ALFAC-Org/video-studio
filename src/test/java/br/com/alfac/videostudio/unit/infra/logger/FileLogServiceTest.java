package br.com.alfac.videostudio.unit.infra.logger;

import br.com.alfac.videostudio.infra.logger.FileLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileLogServiceTest {

    private FileLogService fileLogService;
    private String logFilePath = "test-log.txt";

    @BeforeEach
    public void setUp() {
        fileLogService = new FileLogService();
        ReflectionTestUtils.setField(fileLogService, "logFilePath", logFilePath);
    }

    @Test
    public void testSaveLog() throws IOException {
        Long usuarioId = 1L;
        String resource = "TestResource";
        LocalDateTime datetime = LocalDateTime.now();

        fileLogService.saveLog(usuarioId, resource, datetime);

        File logFile = new File(logFilePath);
        assertTrue(logFile.exists());

        logFile.delete();
    }
}

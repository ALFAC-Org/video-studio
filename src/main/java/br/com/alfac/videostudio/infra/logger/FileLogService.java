package br.com.alfac.videostudio.infra.logger;

import br.com.alfac.videostudio.core.domain.service.LogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class FileLogService implements LogService {

@Value("${log.file.path}")
private String logFilePath;

@Override
    public void saveLog(Long usuarioId, String resource, LocalDateTime datetime) {
        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.write(String.format("usuarioId: %s, Resource: %s, DateTime: %s%n", usuarioId, resource, datetime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
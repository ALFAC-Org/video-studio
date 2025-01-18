package br.com.alfac.videostudio.infra.logger;


import br.com.alfac.videostudio.core.domain.service.LogService;
import br.com.alfac.videostudio.infra.persistence.LogEntity;
import br.com.alfac.videostudio.infra.persistence.LogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DatabaseLogService implements LogService {

    private final LogRepository logRepository;

    public DatabaseLogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void saveLog(Integer usuarioId, String resource, LocalDateTime datetime) {
        LogEntity logeEntity = new LogEntity(usuarioId, resource, datetime);
        logRepository.save(logeEntity);
    }
}

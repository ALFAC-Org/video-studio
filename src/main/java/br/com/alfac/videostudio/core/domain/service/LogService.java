package br.com.alfac.videostudio.core.domain.service;

import java.time.LocalDateTime;

public interface LogService {
    void saveLog(Long usuarioId, String resource, LocalDateTime datetime);
}

package br.com.alfac.videostudio.infra.config;

import br.com.alfac.videostudio.core.domain.service.LogService;
import br.com.alfac.videostudio.infra.logger.DatabaseLogService;
import br.com.alfac.videostudio.infra.logger.FileLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class LogServiceConfig {

    @Autowired
    private DatabaseLogService databaseLogService;

    @Autowired
    private FileLogService fileLogService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public LogService logService() {
        if (isDatabaseAvailable()) {
            return databaseLogService;
        } else {
            return fileLogService;
        }
    }

    private boolean isDatabaseAvailable() {
        try (Connection connection = dataSource.getConnection()) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}

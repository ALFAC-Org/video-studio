package br.com.alfac.videostudio.infra.interceptor;

import br.com.alfac.videostudio.core.domain.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final LogService logService;

    public LogInterceptor(@Qualifier("fileLogService") LogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer usuarioId = (Integer) request.getAttribute("usuarioId");

        String resource = request.getRequestURI();
        LocalDateTime datetime = LocalDateTime.now();

        logService.saveLog(usuarioId, resource, datetime);

        return true;
    }
}
package br.com.alfac.videostudio.infra.interceptor;

import br.com.alfac.videostudio.core.application.adapters.gateways.IUsuarioLogado;
import br.com.alfac.videostudio.core.domain.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final LogService logService;

    private final IUsuarioLogado usuarioLogado;

    public LogInterceptor(LogService logService, IUsuarioLogado usuarioLogado) {
        this.logService = logService;
        this.usuarioLogado = usuarioLogado;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long usuarioId = null;

        try {
            usuarioId = usuarioLogado.getUsuarioLogado().id();
        } catch (Exception e) {
            // Log the exception if necessary
        }

        String resource = request.getRequestURI();
        LocalDateTime datetime = LocalDateTime.now();

        logService.saveLog(usuarioId, resource, datetime);

        return true;
    }
}
package br.com.alfac.videostudio.infra.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.alfac.videostudio.infra.security.JwtTokenProvider;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider tokenProvider;

    @Autowired
    public JwtAuthInterceptor(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Extrair o token do cabeçalho Authorization
        String token = getTokenFromRequest(request);

        if (token != null && tokenProvider.validateToken(token)) {
            // Se o token for válido, extrai o username
            String username = tokenProvider.getUsernameFromToken(token);

            // Configurar o contexto de segurança
            RequestContextHolder.currentRequestAttributes().setAttribute("username", username, RequestAttributes.SCOPE_REQUEST);
        } else {
            // Se o token não for válido, retorne 401 (não autorizado)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    // Método para extrair o token do cabeçalho Authorization
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer "
        }
        return null;
    }

}
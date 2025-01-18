package br.com.alfac.videostudio.infra.config;

import br.com.alfac.videostudio.infra.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
//                .excludePathPatterns("/api/v1/usuarios/**")
                .excludePathPatterns("/api/v1/login/**")
                .excludePathPatterns("/api/v1/health-check/**")
                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**", "/webjars/**", "/api-docs/**");
    }
}

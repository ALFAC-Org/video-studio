//package br.com.alfac.videostudio.infra.config;
//
//import br.com.alfac.videostudio.infra.interceptor.JwtAuthInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    private final JwtAuthInterceptor jwtAuthInterceptor;
//
//    public WebConfig(JwtAuthInterceptor jwtAuthInterceptor) {
//        this.jwtAuthInterceptor = jwtAuthInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtAuthInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/api/v1/usuarios/**")
//                .excludePathPatterns("/api/v1/login/**")
//                .excludePathPatterns("/api/v1/health-check/**")
//                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**", "/webjars/**", "/api-docs/**");
//
//    }
//}
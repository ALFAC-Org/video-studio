package br.com.alfac.videostudio.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        Info info = new Info();
        info.title("API - Aplicação Video Studio - ALFAC");
        info.description("API da aplicação Video Studio");
        info.version("0.2");

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("auth");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("auth");


        return new OpenAPI().info(info)
                .addSecurityItem(securityRequirement)
                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("auth", securityScheme));
    }
    
}

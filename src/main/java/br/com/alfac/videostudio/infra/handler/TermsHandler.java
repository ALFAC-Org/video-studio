package br.com.alfac.videostudio.infra.handler;

import br.com.alfac.videostudio.core.application.usecases.ErroProcessamentoVideoUseCase;
import br.com.alfac.videostudio.infra.config.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/v1/terms")
@Tag(name = "Terms", description = "Métodos relacionados aos termos de uso")
public class TermsHandler {

    private static final Logger logger = LoggerFactory.getLogger(TermsHandler.class);

    public TermsHandler() throws IOException {
    }

    @Operation(summary = "Retorna os termos de uso da aplicação", description = "Retorna os termos de uso da aplicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Termos de uso retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro ao acessar os termos de uso", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> consultarTermosDeUso() throws IOException {
        try {
            logger.debug("[TermsHandler] Getting terms of use");

            Resource resource = new ClassPathResource("static/terms/terms-of-use.json");
            logger.debug("[TermsHandler] Resource is: {}", resource);
            
            String content = new String(Files.readAllBytes(resource.getFile().toPath()));
            logger.debug("[TermsHandler] Content is available: {}", content);

            return ResponseEntity.ok(content);
        } catch (IOException e) {
            logger.error("[TermsHandler] Error to get terms: {}",
                        e);
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
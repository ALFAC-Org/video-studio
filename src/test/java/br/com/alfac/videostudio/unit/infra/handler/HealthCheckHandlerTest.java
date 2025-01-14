package br.com.alfac.videostudio.unit.infra.handler;

import br.com.alfac.videostudio.infra.handler.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alfac.videostudio.infra.config.exception.ApiErrorItem;
import br.com.alfac.videostudio.infra.handler.HealthCheckHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HealthCheckHandlerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HealthCheckHandler healthCheckHandler;

    @MockBean
    private ApiErrorItem apiError;

    @Value("${application.version}")
    private String applicationVersion = "1.0.0";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(healthCheckHandler)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void deveRetornarHealthCheckComSucesso() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/v1/health-check")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Aplicação está funcionando. Veja as versões atuais."));
    }
}

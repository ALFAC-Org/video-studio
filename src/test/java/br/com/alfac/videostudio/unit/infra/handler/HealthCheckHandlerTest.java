package br.com.alfac.videostudio.unit.infra.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.alfac.videostudio.infra.config.exception.ApiErrorItem;
import br.com.alfac.videostudio.infra.handler.HealthCheckHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthCheckHandler.class)
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
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
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

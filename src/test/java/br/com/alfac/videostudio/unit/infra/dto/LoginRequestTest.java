package br.com.alfac.videostudio.unit.infra.dto;

import br.com.alfac.videostudio.infra.dto.LoginRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginRequestTest {

    @Test
    public void testEmail() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("teste@teste.com");
        assertEquals("teste@teste.com", loginRequest.getEmail());
    }

    @Test
    public void testPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("Teste@123");
        assertEquals("Teste@123", loginRequest.getPassword());
    }

    @Test
    public void testEmptyEmail() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("");
        assertNotNull(loginRequest.getEmail());
    }

    @Test
    public void testEmptyPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("");
        assertNotNull(loginRequest.getPassword());
    }
}

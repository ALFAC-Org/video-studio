package br.com.alfac.videostudio.unit.infra.dto;

import br.com.alfac.videostudio.infra.dto.UsuarioRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioRequestTest {

    @Test
    public void testName() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setName("John Doe");
        assertEquals("John Doe", usuarioRequest.getName());
    }

    @Test
    public void testEmail() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setEmail("teste@teste.com");
        assertEquals("teste@teste.com", usuarioRequest.getEmail());
    }

    @Test
    public void testPassword() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setPassword("Teste@123");
        assertEquals("Teste@123", usuarioRequest.getPassword());
    }

    @Test
    public void testEmptyName() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setName("");
        assertNotNull(usuarioRequest.getName());
    }

    @Test
    public void testEmptyEmail() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setEmail("");
        assertNotNull(usuarioRequest.getEmail());
    }

    @Test
    public void testEmptyPassword() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setPassword("");
        assertNotNull(usuarioRequest.getPassword());
    }
}

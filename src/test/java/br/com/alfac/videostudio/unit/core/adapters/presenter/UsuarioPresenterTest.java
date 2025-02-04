package br.com.alfac.videostudio.unit.core.adapters.presenter;

import br.com.alfac.videostudio.core.application.adapters.presenter.UsuarioPresenter;
import br.com.alfac.videostudio.core.application.dto.UsuarioDTO;
import br.com.alfac.videostudio.core.domain.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class UsuarioPresenterTest {

    @Test
    public void testMapearParaUsuarioDTO() {
        UUID uuid = UUID.randomUUID();
        Usuario usuario = new Usuario();
        usuario.setNome("John Doe");
        usuario.setEmail("john.doe@example.com");
        usuario.setUuid(uuid);

        UsuarioDTO usuarioDTO = UsuarioPresenter.mapearParaUsuarioDTO(usuario);

        assertEquals("John Doe", usuarioDTO.getName());
        assertEquals("john.doe@example.com", usuarioDTO.getEmail());
        assertEquals(uuid, usuarioDTO.getUuid());
    }
}

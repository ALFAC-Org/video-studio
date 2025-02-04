package br.com.alfac.videostudio.unit.core.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioUsuarioGateway;
import br.com.alfac.videostudio.core.application.usecases.ObterUsuarioPorUsernameUseCase;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ObterUsuarioPorUsernameUseCaseTest {

    private RepositorioUsuarioGateway usuarioRepository;
    private ObterUsuarioPorUsernameUseCase obterUsuarioPorUsernameUseCase;

    @BeforeEach
    public void setUp() {
        usuarioRepository = Mockito.mock(RepositorioUsuarioGateway.class);
        obterUsuarioPorUsernameUseCase = new ObterUsuarioPorUsernameUseCase(usuarioRepository);
    }

    @Test
    public void testExecuteSuccess() throws VideoStudioException {
        String username = "test@example.com";
        Usuario usuario = new Usuario();
        when(usuarioRepository.consultarUsuarioPorEmail(username)).thenReturn(Optional.of(usuario));

        Usuario result = obterUsuarioPorUsernameUseCase.execute(username);

        assertNotNull(result);
        assertEquals(usuario, result);
        verify(usuarioRepository, times(1)).consultarUsuarioPorEmail(username);
    }

    @Test
    public void testExecuteUserNotFound() {
        String username = "test@example.com";
        when(usuarioRepository.consultarUsuarioPorEmail(username)).thenReturn(Optional.empty());

        VideoStudioException exception = assertThrows(VideoStudioException.class, () -> {
            obterUsuarioPorUsernameUseCase.execute(username);
        });

        assertEquals(exception.getMessage(), "Usuário já cadastrado");
        verify(usuarioRepository, times(1)).consultarUsuarioPorEmail(username);
    }
}

package br.com.alfac.videostudio.unit.core.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioUsuarioGateway;
import br.com.alfac.videostudio.core.application.usecases.CadastrarUsuarioUseCase;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.utils.UsuarioHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarUsuarioUseCaseTest {

    @Mock
    private RepositorioUsuarioGateway repositorioUsuarioGateway;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    @Test
    void deveCadastrarUsuario() throws VideoStudioException {
        // ArrangeVideoStudioException
        Usuario usuario = UsuarioHelper.criarUsuario();
        
        when(repositorioUsuarioGateway.cadastrarUsuario(any(Usuario.class))).thenReturn(usuario);

        //Act
        Usuario usuarioRetornado = cadastrarUsuarioUseCase.execute(usuario);

        //Assert
        assertThat(usuarioRetornado).isInstanceOf(Usuario.class).isNotNull();
        assertThat(usuarioRetornado.getNome()).isEqualTo(usuario.getNome());
        assertThat(usuarioRetornado.getEmail()).isEqualTo(usuario.getEmail());
        assertThat(usuarioRetornado.getUuid()).isNotNull();

        verify(repositorioUsuarioGateway, times(1)).cadastrarUsuario(any(Usuario.class));
    }

    @Test
    void deveGerarExcecaoQuandoUsuarioJaCadastrado() {
        //Arrange
        Usuario usuario = UsuarioHelper.criarUsuario();

        when(repositorioUsuarioGateway.emailJaCadastrado(any(String.class))).thenReturn(true);

        //Act/Assert
        assertThatThrownBy(() -> new CadastrarUsuarioUseCase(repositorioUsuarioGateway, passwordEncoder).execute(usuario))
                .isInstanceOf(VideoStudioException.class)
                .hasMessage("Usuário já cadastrado");
    }

    @Test
    void deveGerarExcecaoQuandoUsuarioIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> cadastrarUsuarioUseCase.execute(null));
    }

}

package br.com.alfac.videostudio.unit.infra.gateways;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.infra.gateways.RepositorioUsuarioGatewayImpl;
import br.com.alfac.videostudio.infra.persistence.UsuarioEntity;
import br.com.alfac.videostudio.infra.persistence.UsuarioEntityRepository;
import br.com.alfac.videostudio.utils.UsuarioHelper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepositorioUsuarioGatewayImplTest {

    @Mock
    private UsuarioEntityRepository usuarioEntityRepository;

    @InjectMocks
    private RepositorioUsuarioGatewayImpl repositorioUsuarioGateway;
 
    @BeforeEach
    void setUp() {
        repositorioUsuarioGateway = new RepositorioUsuarioGatewayImpl(usuarioEntityRepository);
    }

    @Test
    void devePermitirConsultarUsuarioPorEmail() throws VideoStudioException {
        //Arrange
        Usuario usuario = UsuarioHelper.criarUsuario();
        UsuarioEntity usuarioEntity = UsuarioHelper.criarUsuarioEntity(usuario);

        when(usuarioEntityRepository.findByEmail(anyString())).thenReturn(Optional.of(usuarioEntity));

        //Act
        var usuarioOpcional = repositorioUsuarioGateway.consultarUsuarioPorEmail("john.doe@alfac.com");

        //Assert
        usuarioOpcional.ifPresent(usuarioArmazenado -> {
            assertThat(usuarioArmazenado.getEmail())
                .isEqualTo(usuario.getEmail());
            assertThat(usuarioArmazenado.getNome())
                .isEqualTo(usuario.getNome());
            });

        verify(usuarioEntityRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void devePermitirVerificarEmailJaCadastrado() throws VideoStudioException {
        //Arrange
        when(usuarioEntityRepository.existsByEmail(anyString())).thenReturn(true);

        //Act
        boolean emailJaCadastrado = repositorioUsuarioGateway.emailJaCadastrado("john.doe@alfac.com");

        //Assert
        assertThat(emailJaCadastrado).isTrue();

        verify(usuarioEntityRepository, times(1)).existsByEmail(anyString());
    }

    @Test
    void devePermitirCadastrarUsuario() throws VideoStudioException {
        // Arrange
        Usuario usuario = UsuarioHelper.criarUsuario();
        UsuarioEntity usuarioEntity = UsuarioHelper.criarUsuarioEntity(usuario);

        when(usuarioEntityRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        // Act
        var usuarioSalvo = repositorioUsuarioGateway.cadastrarUsuario(usuario);

        // Assert
        assertThat(usuarioSalvo).isInstanceOf(Usuario.class).isNotNull();
        assertThat(usuarioSalvo).extracting(Usuario::getNome).isEqualTo(usuario.getNome());
        assertThat(usuarioSalvo).extracting(Usuario::getEmail).isEqualTo(usuario.getEmail());
        
        verify(usuarioEntityRepository, times(1)).save(any(UsuarioEntity.class));
    }

}
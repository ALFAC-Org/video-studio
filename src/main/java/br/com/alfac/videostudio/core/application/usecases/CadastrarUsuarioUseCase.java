package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioUsuarioGateway;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.core.exception.usuario.UsuarioError;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CadastrarUsuarioUseCase {

    private final RepositorioUsuarioGateway usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public CadastrarUsuarioUseCase(final RepositorioUsuarioGateway usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario execute(Usuario usuario) throws VideoStudioException {

        if (usuarioRepository.emailJaCadastrado(usuario.getEmail())) {
            throw new VideoStudioException(UsuarioError.USUARIO_JA_CADASTRADO);
        }
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioRepository.cadastrarUsuario(usuario);
    }
}

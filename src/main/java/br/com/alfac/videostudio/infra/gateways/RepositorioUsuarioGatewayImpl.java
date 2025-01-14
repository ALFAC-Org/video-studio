package br.com.alfac.videostudio.infra.gateways;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioUsuarioGateway;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.infra.mapper.UsuarioEntityMapper;
import br.com.alfac.videostudio.infra.persistence.UsuarioEntity;
import br.com.alfac.videostudio.infra.persistence.UsuarioEntityRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RepositorioUsuarioGatewayImpl implements RepositorioUsuarioGateway {
    
    private final UsuarioEntityRepository usuarioEntityRepository;

    public RepositorioUsuarioGatewayImpl(final UsuarioEntityRepository usuarioEntityRepository) {
        this.usuarioEntityRepository = usuarioEntityRepository;
    }

    @Override
    public Optional<Usuario> consultarUsuarioPorUsername(final String username) {
        Optional<UsuarioEntity> usuarioEntityOpt = usuarioEntityRepository.findByUsername(username);
        return montarUsuario(usuarioEntityOpt);
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario){
        UsuarioEntity usuarioEntity = UsuarioEntityMapper.INSTANCE.toEntity(usuario);
        usuarioEntity.setUuid(UUID.randomUUID());

        UsuarioEntity usuarioCadastrado = usuarioEntityRepository.save(usuarioEntity);

        return UsuarioEntityMapper.INSTANCE.toDomain(usuarioCadastrado);
    }

    protected Optional<Usuario> montarUsuario(Optional<UsuarioEntity> videoEntityOpt) {
        Optional<Usuario> usuarioOpt = Optional.empty();

        if (videoEntityOpt.isPresent()) {
            UsuarioEntity usuarioEntity = videoEntityOpt.get();

            Usuario usuario = UsuarioEntityMapper.INSTANCE.toDomain(usuarioEntity);

            usuarioOpt = Optional.of(usuario);
        }

        return usuarioOpt;
    }
}
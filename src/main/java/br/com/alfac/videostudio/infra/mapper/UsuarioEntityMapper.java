package br.com.alfac.videostudio.infra.mapper;

import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.infra.persistence.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntityMapper INSTANCE = Mappers.getMapper(UsuarioEntityMapper.class);

    UsuarioEntity toEntity(Usuario usuario);

    Usuario toDomain(UsuarioEntity usuario);

}

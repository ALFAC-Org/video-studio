package br.com.alfac.videostudio.infra.mapper;

import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.infra.dto.UsuarioRequest;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "nome", source = "name")
    @Mapping(target = "senha", source = "password")
    Usuario toDomain(UsuarioRequest usuarioRequest);

}

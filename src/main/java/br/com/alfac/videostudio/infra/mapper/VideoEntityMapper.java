package br.com.alfac.videostudio.infra.mapper;

import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.infra.persistence.VideoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VideoEntityMapper {

    VideoEntityMapper INSTANCE = Mappers.getMapper(VideoEntityMapper.class);

    VideoEntity toEntity(Video video);

    Video toDomain(VideoEntity video);

}

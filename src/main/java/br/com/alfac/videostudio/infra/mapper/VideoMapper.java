package br.com.alfac.videostudio.infra.mapper;

import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.infra.dto.VideoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);
    VideoDTO toDTO(VideoRequest videoRequest);

}

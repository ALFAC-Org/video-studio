package br.com.alfac.videostudio.infra.gateways;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.infra.mapper.VideoEntityMapper;
import br.com.alfac.videostudio.infra.persistence.VideoEntity;
import br.com.alfac.videostudio.infra.persistence.VideoEntityRepository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RepositorioVideoGatewayImpl implements RepositorioVideoGateway {
    
    private final VideoEntityRepository videoEntityRepository;
    private final VideoEntityMapper videoEntityMapper;

    public RepositorioVideoGatewayImpl(final VideoEntityRepository videoEntityRepository, final VideoEntityMapper videoEntityMapper) {
        this.videoEntityRepository = videoEntityRepository;
        this.videoEntityMapper = videoEntityMapper;
    }

    @Override
    public Optional<Video> consultarVideoPorUuId(final UUID id) {
        Optional<VideoEntity> videoEntityOpt = videoEntityRepository.findByUuid(id);
        return montarVideo(videoEntityOpt);
    }

    @Override
    public List<Video> listarVideosUsuario() {
        var videoEntities = videoEntityRepository.findAll();

        List<Video> videos = new ArrayList<>();

        for (VideoEntity videoEntity : videoEntities) {
            Video video = videoEntityMapper.toDomain(videoEntity);
            videos.add(video);
        }

        return videos;
    }

    @Override
    public Video registrarUploadVideo(Video video){
        VideoEntity videoEntity = VideoEntityMapper.INSTANCE.toEntity(video);
        videoEntity.setUuid(UUID.randomUUID());

        VideoEntity videoCadastrado = videoEntityRepository.save(videoEntity);

        return VideoEntityMapper.INSTANCE.toDomain(videoCadastrado);
    }

    protected Optional<Video> montarVideo(Optional<VideoEntity> videoEntityOpt) {
        Optional<Video> videoOpt = Optional.empty();

        if (videoEntityOpt.isPresent()) {
            VideoEntity videoEntity = videoEntityOpt.get();

            Video video = VideoEntityMapper.INSTANCE.toDomain(videoEntity);

            videoOpt = Optional.of(video);
        }

        return videoOpt;
    }
}
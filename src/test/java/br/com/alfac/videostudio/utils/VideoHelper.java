package br.com.alfac.videostudio.utils;

import java.util.UUID;

import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.infra.dto.VideoRequest;
import br.com.alfac.videostudio.infra.persistence.VideoEntity;

public abstract class VideoHelper {
    
    public static VideoRequest criarVideoRequest() {
        VideoRequest videoRequest = new VideoRequest();

        videoRequest.setNome("Video Teste");

        return videoRequest;
    }

    public static VideoDTO criarVideoDTO() {
        return criarVideoDTO(null, null);
    }

    public static VideoDTO criarVideoDTO(Video video) {
        VideoDTO videoDTO = new VideoDTO();

        videoDTO.setId(video.getId());
        videoDTO.setNome(video.getNome());
        videoDTO.setStatus(video.getStatus());

        return videoDTO;
    }

    public static VideoDTO criarVideoDTO(String nome, StatusVideo status) {
        VideoDTO videoDTO = new VideoDTO();

        videoDTO.setUuId(UUID.randomUUID());
        videoDTO.setNome(nome != null && !nome.isEmpty() ? nome : "Video Teste");
        videoDTO.setStatus(status != null ? status : StatusVideo.PENDENTE);

        return videoDTO;
    }

    public static Video criarVideo() {
        return criarVideo(null, null, null);
    }

    public static Video criarVideo(Long usuarioId, String nome, StatusVideo status) {
        Video video = new Video(usuarioId, nome);

        video.setUuid(UUID.randomUUID());
        video.setUsuarioId(usuarioId != null ? usuarioId : 1L);
        video.setNome(nome != null && !nome.isEmpty() ? nome : "Video Teste");
        video.setStatus(status != null ? status : StatusVideo.PENDENTE);

        return video;
    }

    
    public static VideoEntity criarVideoEntity(Video video) {
        VideoEntity videoEntity = new VideoEntity();

        videoEntity.setUsuarioId(video.getUsuarioId());
        videoEntity.setNome(video.getNome());
        videoEntity.setStatus(video.getStatus());
        videoEntity.setUuid(video.getUuid());

        return videoEntity;
    }

}

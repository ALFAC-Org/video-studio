package br.com.alfac.videostudio.utils;

import java.util.UUID;

import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.infra.dto.VideoRequest;

public abstract class VideoHelper {
    
    public static VideoRequest criarVideoRequest() {
        VideoRequest videoRequest = new VideoRequest();

        videoRequest.setNome("Ayrton Senna");

        return videoRequest;
    }

    public static VideoDTO criarVideoDTO() {
        return criarVideoDTO(null, null, null);
    }

    public static VideoDTO criarVideoDTO(Video video) {
        VideoDTO videoDTO = new VideoDTO();

        videoDTO.setId(video.getId());
        videoDTO.setNome(video.getNome());
        videoDTO.setStatus(video.getStatus());

        return videoDTO;
    }

    public static VideoDTO criarVideoDTO(String cpf, String nome, String email) {
        VideoDTO videoDto = new VideoDTO();

        videoDto.setNome(nome != null && !nome.isEmpty() ? nome : "John Doe");

        return videoDto;
    }

    public static Video criarVideo() {
        return criarVideo(null, null, null);
    }

    public static Video criarVideo(String cpf, String nome, String email) {
        Video video = new Video();
        long id = 123;

        video.setId(id);
        video.setUuid(UUID.randomUUID());
        video.setNome(nome != null && !nome.isEmpty() ? nome : "John Doe");

        return video;
    }

}

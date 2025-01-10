package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.domain.Video;

public class UploadVideoUseCase {

    private final RepositorioVideoGateway videoRepository;

    public UploadVideoUseCase(final RepositorioVideoGateway videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video execute(VideoDTO videoDTO) {
        Video video = new Video();

        video.setNome(videoDTO.getNome());

        return videoRepository.registrarUploadVideo(video);
    }
}

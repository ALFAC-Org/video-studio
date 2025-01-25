package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Video;

import java.util.Optional;
import java.util.UUID;

public class AtualizarStatusVideoUseCase {

    private final RepositorioVideoGateway videoRepository;


    public AtualizarStatusVideoUseCase(final RepositorioVideoGateway videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void execute(UUID uuid, String status) {


        Optional<Video> videoCadastrado = videoRepository.consultarVideoPorUuId(uuid);

        if (videoCadastrado.isPresent()) {
            Video video = videoCadastrado.get();
            videoRepository.atualizarStatus(video.getId(), StatusVideo.valueOf(status));
        }


    }
}

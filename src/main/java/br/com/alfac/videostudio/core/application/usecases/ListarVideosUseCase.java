package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;

import java.util.List;

public class ListarVideosUseCase {

    private final RepositorioVideoGateway videoRepository;


    public ListarVideosUseCase(final RepositorioVideoGateway videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> execute(Long usuarioId) throws VideoStudioException {
        List<Video> videoList = videoRepository.listarVideosUsuario(usuarioId);
        return videoList;
    }

}

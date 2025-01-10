package br.com.alfac.videostudio.core.application.adapters.controller;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.adapters.presenter.VideoPresenter;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.usecases.UploadVideoUseCase;
import br.com.alfac.videostudio.core.application.usecases.ListarVideosUseCase;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;

import java.util.List;

public class ControladorVideo {

    private final RepositorioVideoGateway repositorioVideoGateway;

    public ControladorVideo(final RepositorioVideoGateway repositorioVideoGateway) {
        this.repositorioVideoGateway = repositorioVideoGateway;
    }

    public List<VideoDTO> listarVideosUsuario() throws VideoStudioException {
        ListarVideosUseCase listarVideosUseCase = new ListarVideosUseCase(this.repositorioVideoGateway);
        List<Video> videoList = listarVideosUseCase.execute();
        return VideoPresenter.mapearParaVideoDTOList(videoList);
    }

    public VideoDTO uploadVideo(VideoDTO video) {
        UploadVideoUseCase uploadVideoUseCase = new UploadVideoUseCase(this.repositorioVideoGateway);
        Video videoCadastrado = uploadVideoUseCase.execute(video);
        return VideoPresenter.mapearParaVideoDTO(videoCadastrado);
    }
}

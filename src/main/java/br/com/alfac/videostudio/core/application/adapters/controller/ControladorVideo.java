package br.com.alfac.videostudio.core.application.adapters.controller;

import br.com.alfac.videostudio.core.application.adapters.presenter.VideoPresenter;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.usecases.UploadVideoUseCase;
import br.com.alfac.videostudio.core.application.usecases.ListarVideosUseCase;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;

import java.util.List;

public class ControladorVideo {

    private final ListarVideosUseCase listarVideosUseCase;
    private final UploadVideoUseCase uploadVideoUseCase;

    public ControladorVideo(final ListarVideosUseCase listarVideosUseCase,
            final UploadVideoUseCase uploadVideoUseCase) {
        this.listarVideosUseCase = listarVideosUseCase;
        this.uploadVideoUseCase = uploadVideoUseCase;
    }

    public List<VideoDTO> listarVideosUsuario() throws VideoStudioException {
        List<Video> videoList = listarVideosUseCase.execute();
        return VideoPresenter.mapearParaVideoDTOList(videoList);
    }

    public VideoDTO uploadVideo(VideoDTO video) {
        Video videoCadastrado = uploadVideoUseCase.execute(video);
        return VideoPresenter.mapearParaVideoDTO(videoCadastrado);
    }
}

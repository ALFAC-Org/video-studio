package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.core.exception.video.VideoError;
import jakarta.validation.ValidationException;

import java.util.Optional;
import java.util.UUID;

public class DownloadVideoUseCase {

    private final RepositorioVideoGateway videoRepository;
    private final BucketGateway bucketGateway;

    public DownloadVideoUseCase(final RepositorioVideoGateway videoRepository, final BucketGateway bucketGateway) {
        this.videoRepository = videoRepository;
        this.bucketGateway = bucketGateway;
    }

    public String execute(Long usuarioId, UUID videoId) throws VideoStudioException {

        Video video = videoRepository.consultarVideoPorUuIdEUsuarioId(videoId, usuarioId)
                .orElseThrow(() -> new VideoStudioException(VideoError.VIDEO_NOT_FOUND));

        if (!StatusVideo.PROCESSADO.equals(video.getStatus())){
            throw new VideoStudioException(VideoError.VIDEO_NOT_PROCESSED);
        }

        return bucketGateway.generatePresignedUrl(video.getUuid().toString());

    }
}
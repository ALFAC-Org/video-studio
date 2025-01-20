package br.com.alfac.videostudio.core.exception.video;

import br.com.alfac.videostudio.core.exception.StatusCode;
import br.com.alfac.videostudio.core.exception.VideoStudioError;
import br.com.alfac.videostudio.core.exception.VideoStudioErrosImpl;

public class VideoError extends VideoStudioErrosImpl {

    public static final VideoStudioError VIDEO_NOT_FOUND = new VideoError("001", "Vídeo não encontrado", StatusCode.NOT_FOUND);
    public static final VideoStudioError VIDEOS_NOT_FOUND = new VideoError("002", "Vídeos não encontrados", StatusCode.NOT_FOUND);
    public static final VideoStudioError VIDEO_NOT_PROCESSED = new VideoError("003", "Vídeo não processado", StatusCode.BAD_REQUEST);


    private static final String APP_PREFIX = "USER";


    public VideoError(final String errorCode, final String errorMessage) {
        super(APP_PREFIX, errorCode, errorMessage);
    }

    public VideoError(final String errorCode, final String errorMessage, final StatusCode statusCode) {
        super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }


}

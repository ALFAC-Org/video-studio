package br.com.alfac.videostudio.core.exception.cliente;

import br.com.alfac.videostudio.core.exception.VideoStudioError;
import br.com.alfac.videostudio.core.exception.VideoStudioErrosImpl;
import br.com.alfac.videostudio.core.exception.StatusCode;

public class VideoError extends VideoStudioErrosImpl {

    public static final VideoStudioError CLIENTE_NAO_ENCONTRADO = new VideoError("001", "Cliente não encontrado", StatusCode.NOT_FOUND);
    public static final VideoStudioError CLIENTE_NAO_EXISTENTE = new VideoError("002", "Cliente não existente");


    private static final String APP_PREFIX = "CLI";


    public VideoError(final String errorCode, final String errorMessage) {
        super(APP_PREFIX, errorCode, errorMessage);
    }

    public VideoError(final String errorCode, final String errorMessage, final StatusCode statusCode) {
        super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }


}

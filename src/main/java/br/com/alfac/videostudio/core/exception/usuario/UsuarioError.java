package br.com.alfac.videostudio.core.exception.usuario;

import br.com.alfac.videostudio.core.exception.VideoStudioError;
import br.com.alfac.videostudio.core.exception.VideoStudioErrosImpl;
import br.com.alfac.videostudio.core.exception.StatusCode;

public class UsuarioError extends VideoStudioErrosImpl {

    public static final VideoStudioError USUARIO_NAO_ENCONTRADO = new UsuarioError("001", "Usuário não encontrado", StatusCode.NOT_FOUND);
    public static final VideoStudioError USUARIO_JA_CADASTRADO = new UsuarioError("002", "Usuário já cadastrado");


    private static final String APP_PREFIX = "USER";


    public UsuarioError(final String errorCode, final String errorMessage) {
        super(APP_PREFIX, errorCode, errorMessage);
    }

    public UsuarioError(final String errorCode, final String errorMessage, final StatusCode statusCode) {
        super(APP_PREFIX, errorCode, errorMessage, statusCode);
    }


}

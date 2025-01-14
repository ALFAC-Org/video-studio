package br.com.alfac.videostudio.core.exception;


import java.util.List;

public interface VideoStudioError {

    String VIDEO_NOT_FOUND = "Vídeo não encontrado";
    String VIDEOS_NOT_FOUND = "Vídeos não encontrados";

    String getErrorCode();

    String getErrorMessage();

    int getStatusCode();
}

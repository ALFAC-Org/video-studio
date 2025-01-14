package br.com.alfac.videostudio.infra.handler;

import br.com.alfac.videostudio.core.exception.VideoStudioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VideoStudioException.class)
    public ResponseEntity<String> handleVideoStudioException(VideoStudioException ex, WebRequest request) {
        return new ResponseEntity<>("Vídeos não encontrados", HttpStatus.NOT_FOUND);
    }

    // Other exception handlers can be added here
}
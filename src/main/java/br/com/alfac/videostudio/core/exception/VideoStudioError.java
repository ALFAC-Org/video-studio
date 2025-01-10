package br.com.alfac.videostudio.core.exception;


public interface VideoStudioError {

    String getErrorCode();

    String getErrorMessage();

    int getStatusCode();
}

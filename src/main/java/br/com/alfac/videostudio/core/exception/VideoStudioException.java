package br.com.alfac.videostudio.core.exception;

import java.util.List;

public class VideoStudioException extends Exception {

    private final VideoStudioError videoStudioError;
    private final List<VideoStudioError> videoStudioErrors;

    public VideoStudioException(final List<VideoStudioError> videoStudioErrors) {
        super(getMessages(videoStudioErrors));
        this.videoStudioErrors = videoStudioErrors;
        this.videoStudioError = null;
    }

    public VideoStudioException(VideoStudioError videoStudioError) {
        super(videoStudioError.getErrorMessage());
        this.videoStudioError = videoStudioError;
        this.videoStudioErrors = null;
    }

    public VideoStudioException(final List<VideoStudioError> videoStudioErrors, Throwable e) {
        super(getMessages(videoStudioErrors), e);
        this.videoStudioErrors = videoStudioErrors;
        this.videoStudioError = null;
    }
    public VideoStudioException(VideoStudioError videoStudioError, Throwable e) {
        super(videoStudioError.getErrorMessage(), e);
        this.videoStudioError = videoStudioError;
        this.videoStudioErrors = null;
    }


    public VideoStudioException(VideoStudioError videoStudioError, Object... args) {
        super(String.format(videoStudioError.getErrorMessage(), args));
        this.videoStudioError = videoStudioError;
        this.videoStudioErrors = null;
    }

    public VideoStudioException(VideoStudioError videoStudioError, Throwable e, Object... args) {
        super(String.format(videoStudioError.getErrorMessage(), args), e);
        this.videoStudioError = videoStudioError;
        this.videoStudioErrors = null;
    }

    public VideoStudioException(String videoNotFound) {
        super(videoNotFound);
        this.videoStudioError = null;
        this.videoStudioErrors = null;
    }

    public VideoStudioError getVideoStudioError() {
        return videoStudioError;
    }

    public List<VideoStudioError> getVideoStudioErrors() {
        return videoStudioErrors;
    }

    private static String getMessages(final List<VideoStudioError> videoStudioErrors) {
        List<String> mensagensDeErro = videoStudioErrors.stream().map(VideoStudioError::getErrorMessage).toList();
        return String.join(",", mensagensDeErro);
    }
}

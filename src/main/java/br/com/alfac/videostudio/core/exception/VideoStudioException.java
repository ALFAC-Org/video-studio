package br.com.alfac.videostudio.core.exception;

import java.util.List;

public class VideoStudioException extends Exception {

    private final VideoStudioError foodError;
    private final List<VideoStudioError> foodErrors;

    public VideoStudioException(VideoStudioError foodError) {
        super(foodError.getErrorMessage());
        this.foodError = foodError;
        this.foodErrors = null;
    }


    public VideoStudioError getFoodErros() {
        return foodError;
    }

    public List<VideoStudioError> getFoodErrors() {
        return foodErrors;
    }

    private static String getMessages(final List<VideoStudioError> foodErrors) {
        List<String> mensagensDeErro = foodErrors.stream().map(VideoStudioError::getErrorMessage).toList();
        return String.join(",", mensagensDeErro);
    }
}

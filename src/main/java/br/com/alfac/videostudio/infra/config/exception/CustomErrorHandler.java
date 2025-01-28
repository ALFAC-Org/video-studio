package br.com.alfac.videostudio.infra.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class CustomErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        // Log the exception and handle it gracefully
        logger.error("Error in: ", t);
    }
}

package br.com.alfac.videostudio.core.application.util;

import org.apache.tika.mime.MediaType;
import org.apache.tika.Tika;

public class FileValidator {
    private final Tika tika = new Tika();

    public boolean isMp4File(byte[] fileBytes) {
        if (fileBytes == null || fileBytes.length == 0) {
            return false;
        }

        try {
            String detectedType = tika.detect(fileBytes); // Detecta o tipo do conteúdo
            return MediaType.parse(detectedType).equals(MediaType.video("mp4"));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
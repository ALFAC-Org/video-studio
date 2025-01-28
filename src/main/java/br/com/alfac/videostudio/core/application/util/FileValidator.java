package br.com.alfac.videostudio.core.application.util;

import org.apache.tika.Tika;

public class FileValidator {

    public static boolean isMp4File(byte[] fileBytes) {
        if (fileBytes == null || fileBytes.length == 0) {
            return false;
        }

        try {
            Tika tika = new Tika();
            String detectedType = tika.detect(fileBytes); // Detecta o tipo do conteúdo
            return "video/mp4".equals(detectedType);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
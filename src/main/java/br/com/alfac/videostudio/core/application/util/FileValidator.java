package br.com.alfac.videostudio.core.application.util;

import org.apache.tika.mime.MediaType;
import org.apache.tika.Tika;

public class FileValidator {
    private final Tika tika = new Tika();

    private boolean isMp4MimeType(String mimeType) {
        return MediaType.parse(mimeType).equals(MediaType.video("mp4")) ||
               MediaType.parse(mimeType).equals(MediaType.video("quicktime")) ||
               "video/x-m4v".equals(mimeType) ||
               "video/3gpp".equals(mimeType);
    }

    public boolean isMp4File(byte[] fileBytes) {
        if (fileBytes == null || fileBytes.length == 0) {
            return false;
        }

        try {
            String detectedType = tika.detect(fileBytes); // Detecta o tipo do conteúdo

            // System.out.print("|| detectedType: " + detectedType);

            return isMp4MimeType(detectedType);
        } catch (Exception e) {
            System.out.print("[DEBUG] O arquivo informado é inválido " + e.getMessage());

            return false;
        }
    }

}
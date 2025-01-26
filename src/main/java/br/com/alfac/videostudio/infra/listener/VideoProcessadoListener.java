package br.com.alfac.videostudio.infra.listener;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideoProcessado;
import br.com.alfac.videostudio.infra.listener.dto.VideoProcessDTO;
import com.google.gson.Gson;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class VideoProcessadoListener {

    private final ControladorVideoProcessado controladorVideoProcessado;

    public VideoProcessadoListener(ControladorVideoProcessado controladorVideoProcessado) {
        this.controladorVideoProcessado = controladorVideoProcessado;
    }


    @SqsListener(value = "queue-video-processed")
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);

        VideoProcessDTO videoProcessarDTO = new Gson().fromJson(message, VideoProcessDTO.class);

        controladorVideoProcessado.processar(videoProcessarDTO.videoName(), videoProcessarDTO.processingStatus());
    }

}

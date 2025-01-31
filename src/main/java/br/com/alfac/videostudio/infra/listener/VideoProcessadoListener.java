package br.com.alfac.videostudio.infra.listener;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideoProcessado;
import br.com.alfac.videostudio.infra.listener.dto.VideoProcessDTO;
import com.google.gson.Gson;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.SqsException;

@Service
public class VideoProcessadoListener {

    private final ControladorVideoProcessado controladorVideoProcessado;

    public VideoProcessadoListener(ControladorVideoProcessado controladorVideoProcessado) {
        this.controladorVideoProcessado = controladorVideoProcessado;
    }

    @SqsListener(value = "update_processing_status")
    public void receiveMessage(String message) {
        try {
            System.out.println("Mensagem recebida: " + message);

            VideoProcessDTO videoProcessarDTO = new Gson().fromJson(message, VideoProcessDTO.class);

            controladorVideoProcessado.processar(videoProcessarDTO.videoName(), videoProcessarDTO.processingStatus());
        } catch (SqsException e) {
            System.err.println("Erro ao processar mensagem do SQS: " + e.getMessage());
        }

    }

}

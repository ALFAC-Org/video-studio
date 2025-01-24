package br.com.alfac.videostudio.infra.listener;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class VideoProcessadoListener {

    @SqsListener(value = "queue-video-to-process")
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }

}

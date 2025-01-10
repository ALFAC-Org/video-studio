package br.com.alfac.videostudio.infra.listener;

import org.springframework.stereotype.Service;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;

@Service
public class VideoProcessadoListener {

    @SqsListener("sqs.video.processado")
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }

}

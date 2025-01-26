package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Video;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import com.google.gson.Gson;
import io.awspring.cloud.sns.core.SnsTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

public class ErroProcessamentoVideoUseCase {

    private final RepositorioVideoGateway videoRepository;
    private final SnsClient snsClient;
    private final String queueNotificacaoErroProcessamento;

    public ErroProcessamentoVideoUseCase(final RepositorioVideoGateway videoRepository, SnsClient snsClient, String queueNotificacaoErroProcessamento) {
        this.videoRepository = videoRepository;
        this.snsClient = snsClient;
        this.queueNotificacaoErroProcessamento = queueNotificacaoErroProcessamento;
    }

    public void execute(UUID uuid) {


        Optional<Video> videoCadastrado = videoRepository.consultarVideoPorUuId(uuid);

        if (videoCadastrado.isPresent()) {

            String topicArn = "arn:aws:sns:us-east-1:000687245264:envia_email_erro_processamento";
            Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
            messageAttributes.put("email", MessageAttributeValue.builder()
                            .dataType("String")
                            .stringValue("teste@teste.com").build());

            messageAttributes.put("video_name", MessageAttributeValue.builder()
                    .dataType("String")
                    .stringValue(videoCadastrado.get().getUuid().toString()).build());



            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .messageAttributes(messageAttributes)// Use o ARN aqui
                    .build();

            snsClient.publish(request);
            Video video = videoCadastrado.get();
            videoRepository.atualizarStatus(video.getId(), StatusVideo.ERRO);
        }


    }

}

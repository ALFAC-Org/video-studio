package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Video;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import com.google.gson.Gson;
import io.awspring.cloud.sns.core.SnsTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErroProcessamentoVideoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ErroProcessamentoVideoUseCase.class);
    private final RepositorioVideoGateway videoRepository;
    private final SnsClient snsClient;
    private final String queueNotificacaoErroProcessamento;

    private final String topicArn;

    public ErroProcessamentoVideoUseCase(final RepositorioVideoGateway videoRepository, SnsClient snsClient,
            String queueNotificacaoErroProcessamento, String topicArn) {
        this.videoRepository = videoRepository;
        this.snsClient = snsClient;
        this.queueNotificacaoErroProcessamento = queueNotificacaoErroProcessamento;
        this.topicArn = topicArn;
    }

    public void execute(UUID uuid) {
        logger.info("[ErroProcessamentoVideoUseCase] Executing error processing for video with UUID: {}", uuid);

        Optional<Video> videoCadastrado = videoRepository.consultarVideoPorUuId(uuid);

        if (videoCadastrado.isPresent()) {
            try {
                logger.info("[ErroProcessamentoVideoUseCase] Video found with UUID: {}", uuid);
                Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
                messageAttributes.put("email", MessageAttributeValue.builder()
                        .dataType("String")
                        .stringValue("teste@teste.com").build());

                messageAttributes.put("video_name", MessageAttributeValue.builder()
                        .dataType("String")
                        .stringValue(videoCadastrado.get().getUuid().toString()).build());

                logger.info("[ErroProcessamentoVideoUseCase] topicArn: {}", topicArn);

                PublishRequest request = PublishRequest.builder()
                        .topicArn(topicArn)
                        .messageAttributes(messageAttributes)// Use o ARN aqui
                        .build();

                snsClient.publish(request);
                logger.info("[ErroProcessamentoVideoUseCase] Error notification sent for video with UUID: {}", uuid);

                Video video = videoCadastrado.get();
                videoRepository.atualizarStatus(video.getId(), StatusVideo.ERRO);
                logger.info("[ErroProcessamentoVideoUseCase] Video status updated to ERRO for video with UUID: {}",
                        uuid);
            } catch (Exception e) {
                logger.error("[ErroProcessamentoVideoUseCase] Error processing video with UUID: {}", uuid, e);
            }

        } else {
            logger.warn("[ErroProcessamentoVideoUseCase] No video found with UUID: {}", uuid);
        }
    }

}

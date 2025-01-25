package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.QueueGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Video;

import java.util.Optional;
import java.util.UUID;

public class ErroProcessamentoVideoUseCase {

    private final RepositorioVideoGateway videoRepository;
    private final QueueGateway queueGateway;
    private final String queueNotificacaoErroProcessamento;

    public ErroProcessamentoVideoUseCase(final RepositorioVideoGateway videoRepository, final QueueGateway queueGateway, String queueNotificacaoErroProcessamento) {
        this.videoRepository = videoRepository;
        this.queueGateway = queueGateway;
        this.queueNotificacaoErroProcessamento = queueNotificacaoErroProcessamento;
    }

    public void execute(UUID uuid) {


        Optional<Video> videoCadastrado = videoRepository.consultarVideoPorUuId(uuid);

        if (videoCadastrado.isPresent()) {
            queueGateway.sendMessage(queueNotificacaoErroProcessamento, uuid.toString());
            Video video = videoCadastrado.get();
            videoRepository.atualizarStatus(video.getId(), StatusVideo.ERRO);
        }


    }
}

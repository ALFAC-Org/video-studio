package br.com.alfac.videostudio.core.application.usecases;

import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.QueueGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.dto.VideoProcessarDTO;
import br.com.alfac.videostudio.core.application.util.FileValidator;
// import br.com.alfac.videostudio.core.application.util.FileValidator;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
// import br.com.alfac.videostudio.core.exception.video.VideoError;
import br.com.alfac.videostudio.core.exception.video.VideoError;

public class UploadVideoUseCase {

    private final RepositorioVideoGateway videoRepository;

    private final BucketGateway bucketGateway;

    private final String bucketName;

    private final QueueGateway queueGateway;

    private final String queueName;

    private final FileValidator fileValidator;

    public UploadVideoUseCase(final RepositorioVideoGateway videoRepository, final BucketGateway bucketGateway,
            final String bucketName, final QueueGateway queueGateway, final String queueName) {
        this.videoRepository = videoRepository;
        this.bucketGateway = bucketGateway;
        this.bucketName = bucketName;
        this.queueGateway = queueGateway;
        this.queueName = queueName;
        this.fileValidator = new FileValidator();
    }

    private Video getVideoCadastrado(Long usuarioId, VideoDTO videoDTO) {
        Video video = new Video(usuarioId, videoDTO.getNome());

        Video videoCadastrado = videoRepository.registrarUploadVideo(video);

        return videoCadastrado;
    }

    private void sendVideoToBucket(String fileName, byte[] file) {
        bucketGateway.uploadFile("videos/" + fileName, file, bucketName);
    }

    private void sendMessageToQueue(String message) {
        System.out.println("|| Enviando mensagem para a fila: " + queueName);
        queueGateway.sendMessage(queueName, message);
    }

    @Transactional
    public Video execute(Long usuarioId, VideoDTO videoDTO, byte[] file) throws VideoStudioException {
        if (!fileValidator.isMp4File(file)) {
            throw new VideoStudioException(VideoError.VIDEO_INVALID);
        }

        Video videoCadastrado = getVideoCadastrado(usuarioId, videoDTO);

        // Define o nome do arquivo
        String fileName = videoCadastrado.getUuid().toString().concat(".mp4");

        sendVideoToBucket(fileName, file);

        // Cria objeto de mensagem para a fila
        VideoProcessarDTO videoProcessarDTO = new VideoProcessarDTO();
        videoProcessarDTO.setVideoName(fileName);

        // Envia mensagem para a fila para notificar que vídeo está disponível para
        // processamento
        String data = new Gson().toJson(videoProcessarDTO);

        System.out.println("|| Enviando mensagem para a fila: " + data);

        sendMessageToQueue(data);

        return videoCadastrado;
    }
}

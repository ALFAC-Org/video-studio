package br.com.alfac.videostudio.core.application.usecases;

import com.google.gson.Gson;

import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.QueueGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.dto.VideoProcessarDTO;
import br.com.alfac.videostudio.core.application.util.FileValidator;
import br.com.alfac.videostudio.core.domain.Video;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.core.exception.video.VideoError;

public class UploadVideoUseCase {

    private final RepositorioVideoGateway videoRepository;

    private final QueueGateway queueGateway;

    private final String queueName;

    private final BucketGateway bucketGateway;

    private final String bucketName;

    public UploadVideoUseCase(final RepositorioVideoGateway videoRepository, final BucketGateway bucketGateway, final String bucketName, final QueueGateway queueGateway, final String queueName) {
        this.videoRepository = videoRepository;
        this.bucketGateway = bucketGateway;
        this.bucketName = bucketName;
        this.queueGateway = queueGateway;
        this.queueName = queueName;
    }

    public Video execute(Long usuarioId, VideoDTO videoDTO, byte[] file) throws VideoStudioException {

        if(FileValidator.isMp4File(file) == false){
            throw new VideoStudioException(VideoError.VIDEO_INVALID);
        }

        Video video = new Video(usuarioId, videoDTO.getNome());

        Video videoCadastrado = videoRepository.registrarUploadVideo(video);

        //Define o nome do arquivo
        String fileName = videoCadastrado.getUuid().toString();

        //Copia o video para bucket
        bucketGateway.uploadFile("videos/" + fileName, file, bucketName);

        //Cria objeto de mensagem para a fila
        VideoProcessarDTO videoProcessarDTO = new VideoProcessarDTO();
        videoProcessarDTO.setVideoName(fileName);

        //Envia mensagem para a fila para notificar que vídeo está disponível para processamento
        queueGateway.sendMessage(queueName, new Gson().toJson(videoProcessarDTO));

        return videoCadastrado;
    }
}

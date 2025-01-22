package br.com.alfac.videostudio.core.application.usecases;

import br.com.alfac.videostudio.core.application.adapters.gateways.BucketGateway;
import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.domain.Video;

public class UploadVideoUseCase {

    private final RepositorioVideoGateway videoRepository;

    private final BucketGateway bucketGateway;

    private final String bucketName;

    public UploadVideoUseCase(final RepositorioVideoGateway videoRepository, final BucketGateway bucketGateway, final String bucketName) {
        this.videoRepository = videoRepository;
        this.bucketGateway = bucketGateway;
        this.bucketName = bucketName;
    }

    public Video execute(Long usuarioId, VideoDTO videoDTO, byte[] file) {

        Video video = new Video(usuarioId, videoDTO.getNome());

        Video videoCadastrado = videoRepository.registrarUploadVideo(video);

        //Define o nome do arquivo
        String fileName = videoCadastrado.getUuid().toString();

        //Copia o video para bucket
        bucketGateway.uploadFile(fileName, file, bucketName);

        //Usar o uuid para enviar para a fila de processamento
        //videoCadastrado.getUuid();

        return videoCadastrado;
    }
}

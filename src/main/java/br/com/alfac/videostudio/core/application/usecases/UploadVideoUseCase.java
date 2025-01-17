package br.com.alfac.videostudio.core.application.usecases;

import java.util.UUID;

import br.com.alfac.videostudio.core.application.adapters.gateways.RepositorioVideoGateway;
import br.com.alfac.videostudio.core.application.dto.UsuarioDTO;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.domain.Video;

public class UploadVideoUseCase {

    private final RepositorioVideoGateway videoRepository;

    public UploadVideoUseCase(final RepositorioVideoGateway videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video execute(Long usuarioId, VideoDTO videoDTO) {

        Video video = new Video(usuarioId, videoDTO.getNome());

        Video videoCadastrado = videoRepository.registrarUploadVideo(video);

        //Usar o uuid para enviar para a fila de processamento
        //videoCadastrado.getUuid();

        return videoCadastrado;
    }
}

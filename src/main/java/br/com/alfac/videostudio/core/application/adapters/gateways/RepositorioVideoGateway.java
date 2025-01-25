package br.com.alfac.videostudio.core.application.adapters.gateways;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.alfac.videostudio.core.domain.StatusVideo;
import br.com.alfac.videostudio.core.domain.Video;

public interface RepositorioVideoGateway {

    Optional<Video> consultarVideoPorUuId(UUID id);
    Optional<Video> consultarVideoPorUuIdEUsuarioId(UUID id, Long usuarioId);

    List<Video> listarVideosUsuario(Long usuarioId);
    
    Video registrarUploadVideo(Video video);
    void atualizarStatus(Long id, StatusVideo statusVideo);

}

package br.com.alfac.videostudio.infra.handler;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideo;
import br.com.alfac.videostudio.core.application.adapters.gateways.IUsuarioLogado;
import br.com.alfac.videostudio.core.application.dto.DownloadDTO;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.infra.config.exception.ApiError;
import br.com.alfac.videostudio.infra.dto.VideoRequest;
import br.com.alfac.videostudio.infra.mapper.VideoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
@Tag(name = "Video", description = "Métodos para manipulação de vídeos")
public class VideoHandler {

    private final ControladorVideo controladorVideo;
    private final VideoMapper videoMapper;
    private final IUsuarioLogado usuarioLogado;

    public VideoHandler(final ControladorVideo controladorVideo, final VideoMapper videoMapper, final IUsuarioLogado usuarioLogado) {
        this.controladorVideo = controladorVideo;
        this.videoMapper = videoMapper;
        this.usuarioLogado = usuarioLogado;
    }

    @Operation(summary = "Listar videos do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Nenhum video cadastrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VideoDTO>> listarVideosUsuario() throws VideoStudioException {

        return new ResponseEntity<>(controladorVideo.listarVideosUsuario(usuarioLogado.getUsuarioLogado().id()), HttpStatus.OK);
    }

    @Operation(summary = "Upload de vídeo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vídeo cadastrado"),
            @ApiResponse(responseCode = "404", description = "Erro ao cadastrar vídeo", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<VideoDTO> uploadVideo(@RequestParam("fileName") String fileName,
                                                @RequestParam("file") MultipartFile file) throws VideoStudioException, IOException {

        VideoDTO video = controladorVideo.uploadVideo(
                usuarioLogado.getUsuarioLogado().id(), 
                videoMapper.toDTO(new VideoRequest(fileName)),
                file.getBytes());

        return new ResponseEntity<>(video, HttpStatus.CREATED);
    }



    @Operation(summary = "Download de vídeo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Nenhum arquivo encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @GetMapping(path = "/download/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DownloadDTO> downloadVideoUsuario(@PathVariable UUID uuid) throws VideoStudioException {

        DownloadDTO downloadDTO = controladorVideo.downloadVideo(usuarioLogado.getUsuarioLogado().id(), uuid);

        return ResponseEntity.ok(downloadDTO);
    }

}

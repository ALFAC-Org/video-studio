package br.com.alfac.videostudio.infra.handler;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorVideo;
import br.com.alfac.videostudio.core.application.dto.VideoDTO;
import br.com.alfac.videostudio.core.application.usecases.ObterUsuarioPorUsernameUseCase;
import br.com.alfac.videostudio.core.domain.Usuario;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.infra.dto.VideoRequest;
import br.com.alfac.videostudio.infra.mapper.VideoMapper;
import br.com.alfac.videostudio.infra.config.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
@Tag(name = "Video", description = "Métodos para manipulação de vídeos")
public class VideoHandler {

    private final ObterUsuarioPorUsernameUseCase obterUsuarioPorUsernameUseCase;
    private final ControladorVideo controladorVideo;
    private final VideoMapper videoMapper;

    public VideoHandler(final ControladorVideo controladorVideo, final VideoMapper videoMapper, final ObterUsuarioPorUsernameUseCase obterUsuarioPorUsernameUseCase) {
        this.obterUsuarioPorUsernameUseCase = obterUsuarioPorUsernameUseCase;
        this.controladorVideo = controladorVideo;
        this.videoMapper = videoMapper;
    }

    @Operation(summary = "Listar videos do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Nenhum video cadastrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VideoDTO>> listarVideosUsuario(
        @AuthenticationPrincipal UserDetails userDetails) throws VideoStudioException {

        String username = userDetails.getUsername();

        Usuario usuario = obterUsuarioPorUsernameUseCase.execute(username);
        
        return new ResponseEntity<>(controladorVideo.listarVideosUsuario(usuario.getId()), HttpStatus.OK);
    }

    @Operation(summary = "Upload de vídeo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vídeo cadastrado"),
            @ApiResponse(responseCode = "404", description = "Erro ao cadastrar vídeo", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @PostMapping
    public ResponseEntity<VideoDTO> uploadVideo(
        @AuthenticationPrincipal UserDetails userDetails,
        @Valid @RequestBody VideoRequest videoRequest) throws VideoStudioException {

        String username = userDetails.getUsername();

        Usuario usuario = obterUsuarioPorUsernameUseCase.execute(username);        
        
        VideoDTO video = controladorVideo.uploadVideo(usuario.getId(), videoMapper.toDTO(videoRequest));

        return new ResponseEntity<>(video, HttpStatus.CREATED);
    }

    @Operation(summary = "Download de vídeo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Nenhum arquivo encontrado", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @GetMapping(path = "/download/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void downloadVideoUsuario(
        @AuthenticationPrincipal UserDetails userDetails,
        @PathVariable String uuid) throws VideoStudioException {
                
    }

}

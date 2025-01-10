package br.com.alfac.videostudio.infra.handler;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorUsuario;
import br.com.alfac.videostudio.core.application.dto.UsuarioDTO;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.infra.dto.UsuarioRequest;
import br.com.alfac.videostudio.infra.mapper.UsuarioMapper;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuário", description = "Métodos para manipulação de usuários")
public class UsuarioHandler {

    private final ControladorUsuario controladorUsuario;
    private final UsuarioMapper usuarioMapper;

    public UsuarioHandler(final ControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;
        this.usuarioMapper = UsuarioMapper.INSTANCE;
    }

    @Operation(summary = "Cadastro de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado"),
            @ApiResponse(responseCode = "404", description = "Erro ao cadastrar usuário", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(
        @RequestHeader(name = "Authorization") String authorization,
        @Valid @RequestBody UsuarioRequest usuarioRequest) throws VideoStudioException {

        UsuarioDTO usuarioDTO = controladorUsuario.cadastrarUsuario(usuarioMapper.toDTO(usuarioRequest));
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

}

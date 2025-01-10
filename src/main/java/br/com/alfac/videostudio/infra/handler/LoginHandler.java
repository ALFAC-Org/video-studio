package br.com.alfac.videostudio.infra.handler;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorUsuario;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.infra.dto.LoginRequest;
import br.com.alfac.videostudio.infra.mapper.UsuarioMapper;
import br.com.alfac.videostudio.infra.security.JwtTokenProvider;
import br.com.alfac.videostudio.infra.config.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@Tag(name = "Login", description = "Métodos relacionados à autenticação")
public class LoginHandler {

    private final JwtTokenProvider tokenProvider;
    private final ControladorUsuario controladorUsuario;
    private final UsuarioMapper usuarioMapper;

    public LoginHandler(final JwtTokenProvider tokenProvider, final ControladorUsuario controladorUsuario) {
        this.tokenProvider = tokenProvider;
        this.controladorUsuario = controladorUsuario;
        this.usuarioMapper = UsuarioMapper.INSTANCE;
    }

    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @PostMapping
    public String login(@Valid @RequestBody LoginRequest loginRequest) throws VideoStudioException {

        // Validar as credenciais (isso pode ser feito de várias maneiras)
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        //Validar o login

        // Gera o token JWT
        return tokenProvider.generateToken(username);
    }

}

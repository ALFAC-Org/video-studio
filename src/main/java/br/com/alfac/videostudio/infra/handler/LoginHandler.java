package br.com.alfac.videostudio.infra.handler;

import br.com.alfac.videostudio.core.application.adapters.controller.ControladorUsuario;
import br.com.alfac.videostudio.core.exception.VideoStudioException;
import br.com.alfac.videostudio.infra.config.security.JwtUtil;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@Tag(name = "Login", description = "Métodos relacionados à autenticação")
public class LoginHandler {

    private final JwtTokenProvider tokenProvider;
    private final ControladorUsuario controladorUsuario;
    private final UsuarioMapper usuarioMapper;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    public LoginHandler(final JwtTokenProvider tokenProvider, final ControladorUsuario controladorUsuario, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.tokenProvider = tokenProvider;
        this.controladorUsuario = controladorUsuario;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.usuarioMapper = UsuarioMapper.INSTANCE;
    }

    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))
            })})
    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new Exception("Usuário ou senha incorretos", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}

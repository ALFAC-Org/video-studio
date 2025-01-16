package br.com.alfac.videostudio.infra.config.security;

import br.com.alfac.videostudio.infra.persistence.UsuarioEntity;
import br.com.alfac.videostudio.infra.persistence.UsuarioEntityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Supondo que você tenha um repositório de usuários
    private final UsuarioEntityRepository userRepository;

    public CustomUserDetailsService(UsuarioEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("WRITE_PRIVILEGE"));
        return new User(user.getEmail(), user.getSenha(), authorities);
    }
}

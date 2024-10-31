package com.trabalho_av2;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario cadastrarUsuario(Usuario usuario) throws MessagingException {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        emailService.enviarEmail(usuario.getEmail(), "Bem-vindo", "Seu cadastro foi realizado!");
        return usuario;
    }
    public Usuario atualizarUsuario(UUID id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(encoder.encode(usuarioAtualizado.getSenha()));
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    
    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}

package com.trabalho_av2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) throws Exception {
        return usuarioService.cadastrarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}

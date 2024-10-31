package com.trabalho_av2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Bem-vindo ao sistema!"; // Mensagem simples para a página inicial
    }
}
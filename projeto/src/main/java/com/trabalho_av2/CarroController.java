package com.trabalho_av2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carros")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @PostMapping("/cadastro")
    public Carro cadastrarCarro(@RequestBody Carro carro) {
        return carroService.cadastrarCarro(carro);
    }

    @GetMapping
    public List<Carro> listarCarros() {
        return carroService.listarCarros();
    }

    @DeleteMapping("/{id}")
    public void excluirCarro(@PathVariable UUID id) {
        carroService.excluirCarro(id);
    }
}

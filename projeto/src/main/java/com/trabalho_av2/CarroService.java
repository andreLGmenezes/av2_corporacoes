package com.trabalho_av2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public Carro cadastrarCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    public void excluirCarro(UUID id) {
        carroRepository.deleteById(id);
    }
}

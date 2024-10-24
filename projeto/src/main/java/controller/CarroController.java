package controller;

import model.Carro;
import repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    // Listar carros (acessível por ADMIN e USERS)
    @GetMapping
    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    // Cadastrar um novo carro (somente ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Carro cadastrarCarro(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    // Atualizar um carro existente (somente ADMIN)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        return carroRepository.findById(id)
                .map(carro -> {
                    carro.setNome(carroAtualizado.getNome());
                    carro.setMarca(carroAtualizado.getMarca());
                    carro.setPlaca(carroAtualizado.getPlaca());
                    carro.setAno(carroAtualizado.getAno());
                    carro.setPreco(carroAtualizado.getPreco());
                    carro.setCidade(carroAtualizado.getCidade());
                    Carro carroSalvo = carroRepository.save(carro);
                    return ResponseEntity.ok(carroSalvo);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Deletar um carro (somente ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deletarCarro(@PathVariable Long id) {
        return carroRepository.findById(id)
                .map(carro -> {
                    carroRepository.delete(carro);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

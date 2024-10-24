import java.util.List;
import java.util.Optional;

import main.java.Carro;
import main.java.Repositorios.CarroRepository;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping
    public List<Carro> listar() {
        return carroRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Carro cadastrar(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id, @RequestBody Carro carro) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (!carroOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        carro.setId(id);
        carroRepository.save(carro);
        return ResponseEntity.ok(carro);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(@PathVariable Long id) {
        carroRepository.deleteById(id);
    }
}

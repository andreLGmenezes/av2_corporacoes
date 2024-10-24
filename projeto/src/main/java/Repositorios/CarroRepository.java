import java.util.List;

import main.java.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByNomeContaining(String nome);
}

@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String marca;
    private String placa;
    private int ano;
    private double preco;
    private String cidade;

    // Getters e Setters
}

package Joalheria.entity;

public class Colar extends Joia {

    public Colar(Long id, String nome, String material, double peso, double preco, int quantidadeEstoque, String classificacao, double comprimento) {
        super(id, nome, "Colar", material, peso, preco, quantidadeEstoque, classificacao);
        this.comprimento = comprimento;
    }
    private double comprimento;

    // Get-Set
    public double getComprimento() {
        return comprimento;
    }
    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }


    @Override
    public String detalhesExtras() { return String.valueOf(comprimento); }
}

package Joalheria.entity;

public class Anel extends Joia {

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    private int tamanho;

    public Anel(Long id, String nome, String material, double peso, double preco, int quantidadeEstoque, String classificacao, int tamanho) {
        super(id, nome, "Anel", material, peso, preco, quantidadeEstoque, classificacao);
        this.tamanho = tamanho;
    }

    @Override
    public String detalhesExtras() { return String.valueOf(tamanho); }
}
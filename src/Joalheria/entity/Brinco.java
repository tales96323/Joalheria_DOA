package Joalheria.entity;

public class Brinco extends Joia {
    public String getTipoDeFecho() {
        return tipoDeFecho;
    }

    public void setTipoDeFecho(String tipoDeFecho) {
        this.tipoDeFecho = tipoDeFecho;
    }

    private String tipoDeFecho;

    public Brinco(Long id, String nome, String material, double peso, double preco, int quantidadeEstoque, String classificacao, String tipoDeFecho) {
        super(id, nome, "Brinco", material, peso, preco, quantidadeEstoque, classificacao);
        this.tipoDeFecho = tipoDeFecho;
    }

    @Override
    public String detalhesExtras() { return tipoDeFecho; }
}

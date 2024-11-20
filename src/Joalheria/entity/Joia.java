package Joalheria.entity;

public class Joia {

    private Long id;
    private String nome;
    private String tipo;
    private String material;
    private double peso;
    private double preco;
    private int quantidadeEstoque;
    private String classificacao;


    public void getId(Long id) {this.id = id;}
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException ("Preço não pode ser negativo");
        }
        this.preco = preco;
    }
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    public String getClassificacao() {
        return classificacao;
    }
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }


    public Joia(Long id, String nome, String tipo, String material, double peso, double preco, int quantidadeEstoque, String classificacao) {

        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.material = material;
        this.peso = peso;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.classificacao = classificacao;
    }

    public Long getId() {
        return id;
    }
    public String detalhesExtras() {
        return null;
    };
}


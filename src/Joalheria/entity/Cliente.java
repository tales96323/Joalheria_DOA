package Joalheria.entity;

import Joalheria.entity.interfaces.Entity;

public class Cliente implements Entity {
    private Long id;
    private String nome;
    private String nif;
    private String email;
    private String telefone;
    private String endereco;


    public Cliente(Long id, String nome, String nif, String email, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }
    @Override
    public void setId(Object o) {

    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // === Metodo toString() ===
    // Retorna uma representação em String dos detalhes do cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
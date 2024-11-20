package Joalheria.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Classe que representa um pagamento realizado para um pedido - Tem um pedidoID pra relacionar com o Pedido.
public class Pagamento {
    private Long id;
    private double valor;
    private LocalDate data;
    private String metodoPagamento;
    private Long pedidoId;


    public Pagamento(Long id, double valor, LocalDate data, String metodoPagamento, Long pedidoId) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.metodoPagamento = metodoPagamento;
        this.pedidoId = pedidoId;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getMetodoPagamento() {
        return metodoPagamento;
    }
    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
    public Long getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    // Sobrescrita do metodo toString para exibir os detalhes do pagamento
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Pagamento{" +
                "ID=" + id +
                ", Valor=" + valor +
                ", Data=" + data.format(formatter) +
                ", Método de Pagamento='" + metodoPagamento + '\'' +
                ", Pedido ID=" + pedidoId +
                '}';
    }
}

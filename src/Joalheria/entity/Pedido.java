package Joalheria.entity;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class Pedido {
    private Long id;
    private Long clienteId; // Relaciona o pedido com um cliente específico através do ID
    private String data;
    private List<Long> itensComprados;
    private double valorTotal;
    private String status;

    public Pedido(Long id, Long clienteId, String data, List itensComprados, double valorTotal, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.data = data;
        this.itensComprados = itensComprados;
        this.valorTotal = valorTotal;
        this.status = status;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public List<Long> getItensComprados() {
        return itensComprados;
    }
    public void setItensComprados(List<Long> itensComprados) {
        this.itensComprados = itensComprados;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    // Sobrescrita do metodo toString para exibir os detalhes do pedido
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String itens = String.join(", ", itensComprados.stream().map(String::valueOf).toArray(String[]::new));
        return "Pedido{" +
                "ID=" + id +
                ", Cliente ID=" + clienteId +
                ", Data=" + String.format(String.valueOf(formatter)) +
                ", Itens Comprados=" + itens +
                ", Valor Total=" + valorTotal +
                ", Status='" + status + '\'' +
                '}';
    }

    public Long getListaItens() {
        return 0L;
    }
}

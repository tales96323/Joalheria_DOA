package Joalheria.repository;

import Joalheria.entity.Pedido;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PedidoRepository {
    private static final String ARQUIVO_CSV = "pedidos.csv";

    //Salva um pedido no arquivo CSV.
    public void salvarPedido(Pedido pedido) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_CSV, true))) {
            bw.write(formatarPedidoCSV(pedido) + "\n");
        }
    }

    //Recupera todos os pedidos salvos no arquivo CSV.
    public List<Pedido> listarPedidos() throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Pedido pedido = parsePedidoCSV(linha);
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    // Busca um pedido pelo ID no arquivo CSV.
    public Pedido buscarPedidoPorId(Long id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Pedido pedido = parsePedidoCSV(linha);
                if (pedido.getId().equals(id)) {
                    return pedido;
                }
            }
        }
        return null;
    }

    //Formata um objeto Pedido para ser armazenado em formato CSV.
    private String formatarPedidoCSV(Pedido pedido) {
        return pedido.getId() + "," +
                pedido.getClienteId() + "," +
                pedido.getData() + "," +
                pedido.getListaItens() + "," +
                pedido.getValorTotal() + "," +
                pedido.getStatus();
    }

    //Converte uma linha do arquivo CSV em um objeto Pedido.
    private Pedido parsePedidoCSV(String linha) {
        String[] campos = linha.split(",");
        Long id = Long.parseLong(campos[0]);
        Long clienteId = Long.parseLong(campos[1]);
        String data = campos[2];
        String listaItens = campos[3]; // Para simplicidade, itens são tratados como string. Pode ser adaptado conforme o caso.
        double valorTotal = Double.parseDouble(campos[4]);
        String status = campos[5];

        return new Pedido(id, clienteId, data, Collections.singletonList(listaItens), valorTotal, status);
    }

    //Reescreve uma linha do arquivo CSV em um objeto Pedido.
    public void reescreverPedidos(List<Pedido> pedidos) {;
    }
}
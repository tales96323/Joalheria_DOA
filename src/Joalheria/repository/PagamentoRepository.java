package Joalheria.repository;

import Joalheria.entity.Pagamento;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PagamentoRepository {
    private static final String ARQUIVO_CSV = "pagamentos.csv";

     //Salva um pagamento no arquivo CSV.
    public void salvarPagamento(Pagamento pagamento) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_CSV, true))) {
            bw.write(formatarPagamentoCSV(pagamento) + "\n");
        }
    }

    // Recupera todos os pagamentos salvos no arquivo CSV.
    public List<Pagamento> listarPagamentos() throws IOException {
        List<Pagamento> pagamentos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Pagamento pagamento = parsePagamentoCSV(linha);
                pagamentos.add(pagamento);
            }
        }
        return pagamentos;
    }

     //Formata um objeto Pagamento para ser armazenado em formato CSV.
    private String formatarPagamentoCSV(Pagamento pagamento) {
        return pagamento.getId() + "," +
                pagamento.getPedidoId() + "," +
                pagamento.getValor() + "," +
                pagamento.getData() + "," +
                pagamento.getMetodoPagamento();
    }

    //Converte uma linha do arquivo CSV em um objeto Pagamento.
    private Pagamento parsePagamentoCSV(String linha) {
        String[] campos = linha.split(",");
        Long id = Long.parseLong(campos[0]);
        Long pedidoId = Long.parseLong(campos[1]);
        double valor = Double.parseDouble(campos[2]);
        LocalDate data = LocalDate.parse(campos[3]);
        String metodoDePagamento = campos[4];

        return new Pagamento(id, valor, data, metodoDePagamento, pedidoId);
    }

    //Busca um pagamento pelo ID no arquivo CSV.
    public Pagamento buscarPagamentoPorId(Long id) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Pagamento pagamento = parsePagamentoCSV(linha);
                if (pagamento.getId().equals(id)) {
                    return pagamento;
                }
            }
        }
        return null;
    }
}
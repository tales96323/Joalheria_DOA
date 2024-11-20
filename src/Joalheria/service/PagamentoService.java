package Joalheria.service;

import Joalheria.entity.Pagamento;
import Joalheria.repository.PagamentoRepository;

import java.io.IOException;
import java.util.List;



public class PagamentoService {
    public PagamentoRepository pagamentoRepository;


    //Construtor para inicializar o repositório de pagamentos.
    public PagamentoService() {
        this.pagamentoRepository = new PagamentoRepository();
    }
    //Adiciona um novo pagamento.
    public void adicionarPagamento(Pagamento pagamento) {
        try {
            pagamentoRepository.salvarPagamento(pagamento);
            System.out.println("Pagamento adicionado com sucesso: " + pagamento.getValor() + " - Método: " + pagamento.getMetodoPagamento());
        } catch (IOException e) {
            System.err.println("Erro ao salvar pagamento: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao adicionar pagamento: " + e.getMessage());
        }
    }


    //Lista todos os pagamentos armazenados.
    public List<Pagamento> listarPagamentos() throws IOException {
        List<Pagamento> pagamentos = pagamentoRepository.listarPagamentos();
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento encontrado.");
        }
        return pagamentos;
    }
    //Busca um pagamento pelo seu ID.
    public Pagamento buscarPagamentoPorId(Long id) throws IOException {
        Pagamento pagamento = pagamentoRepository.buscarPagamentoPorId(id);
        if (pagamento == null) {
            System.out.println("Pagamento não encontrado para o ID: " + id);
        }
        return pagamento;
    }
}

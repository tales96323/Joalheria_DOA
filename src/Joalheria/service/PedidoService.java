package Joalheria.service;

import Joalheria.entity.Pedido;
import Joalheria.repository.PedidoRepository;
import Joalheria.exception.EntityNotFoundException;

import java.io.IOException;
import java.util.List;


public class PedidoService {
    private final PedidoRepository pedidoRepository;

    //Construtor para inicializar o PedidoService com um repositório de pedidos.
    public PedidoService() {
        this.pedidoRepository = new PedidoRepository();
    }

    //Adiciona um novo pedido.
    public void adicionarPedido(Pedido pedido) {
        try {
            pedidoRepository.salvarPedido(pedido);
            System.out.println("Pedido adicionado com sucesso: ID " + pedido.getId());
        }  catch (IOException e) {
            System.err.println("Erro inesperado ao adicionar pedido: " + e.getMessage());
        }
    }

    //Lista todos os pedidos armazenados.
    public List<Pedido> listarPedidos() throws IOException {
        List<Pedido> pedidos = pedidoRepository.listarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
        }
        return pedidos;
    }

    //Busca um pedido pelo seu ID.
    public Pedido buscarPedidoPorId(Long id) throws IOException, EntityNotFoundException {
        Pedido pedido = pedidoRepository.buscarPedidoPorId(id);
        if (pedido == null) {
            throw new EntityNotFoundException("Pedido com ID " + id + " não encontrado.");
        }
        return pedido;
    }

    // Atualiza o status de um pedido existente.
    public void atualizarStatusPedido(Long id, String status) throws IOException, EntityNotFoundException {
        Pedido pedido = buscarPedidoPorId(id); // Verifica se o pedido existe.
        pedido.setStatus(status);
        atualizarPedido(pedido);
        System.out.println("Status do pedido atualizado com sucesso.");
    }

    // Atualiza os dados de um pedido existente.
    private void atualizarPedido(Pedido pedido) throws IOException {
        List<Pedido> pedidos = pedidoRepository.listarPedidos();
        boolean encontrado = false;

        // Atualiza o pedido na lista
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId().equals(pedido.getId())) {
                pedidos.set(i, pedido);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            // Reescreve o arquivo com a lista atualizada
            pedidoRepository.reescreverPedidos(pedidos);
        } else {
            throw new EntityNotFoundException("Pedido com ID " + pedido.getId() + " não encontrado para atualização.");
        }
    }
}
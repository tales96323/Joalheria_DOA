package Joalheria;

import Joalheria.entity.*;
import Joalheria.service.ClienteService;
import Joalheria.service.FuncionarioService;
import Joalheria.service.JoiaService;
import Joalheria.service.PedidoService;
import Joalheria.service.PagamentoService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws IOException {
        // Inicialização dos serviços
        FuncionarioService funcionarioService = new FuncionarioService();
        ClienteService clienteService = new ClienteService();
        JoiaService joiaService = new JoiaService();
        PedidoService pedidoService = new PedidoService();
        PagamentoService pagamentoService = new PagamentoService();

        // 1. Adicionar Funcionários
        Funcionario.Gerente gerente = new Funcionario.Gerente(1L, "Joana Silva", "123456789", "2023-01-15", 5000.00, 100000.00, "Gerencia");
        Funcionario.Vendedor vendedor1 = new Funcionario.Vendedor(2L, "Carlos Almeida", "987654321", "2023-02-10", 3000.00, 50000.00, 500);
        Funcionario.Vendedor vendedor2 = new Funcionario.Vendedor(3L, "Maria Costa", "112233445", "2023-03-20", 3200.00, 60000.00, 600);
        funcionarioService.adicionarFuncionario(gerente);
        funcionarioService.adicionarFuncionario(vendedor1);
        funcionarioService.adicionarFuncionario(vendedor2);

        // 2. Adicionar Clientes
        Cliente cliente1 = new Cliente(1L, "Alice Santos", "11122233344", "alice@email.com", "123456789", "Rua A, 123");
        Cliente cliente2 = new Cliente(2L, "Bruno Pereira", "55566677788", "bruno@email.com", "987654321", "Rua B, 456");
        Cliente cliente3 = new Cliente(3L, "Carla Lima", "99900011122", "carla@email.com", "567890123", "Rua C, 789");
        clienteService.adicionarCliente(cliente1);
        clienteService.adicionarCliente(cliente2);
        clienteService.adicionarCliente(cliente3);

        // 3. Adicionar Joias
        Joia colar = new Joia(1L, "Colar de Ouro", "Colar", "Ouro", 20.0, 2000.0, 10, "Luxo");
        Joia brinco = new Joia(2L, "Brinco de Prata", "Brinco", "Prata", 10.0, 500.0, 15, "Casual");
        Joia anel = new Joia(3L, "Anel de Diamante", "Anel", "Diamante", 5.0, 10000.0, 5, "Luxo");
        joiaService.adicionarJoia(colar);
        joiaService.adicionarJoia(brinco);
        joiaService.adicionarJoia(anel);

        // 4. Realizar 3 Pedidos
        Pedido pedido1 = new Pedido(1L, 4L, "1/2/3456", Arrays.asList(colar.getId()),2000.0, "pendente");
        Pedido pedido2 = new Pedido(2L, 5L, "1/2/3456", Arrays.asList(brinco.getId(), anel.getId()), 10500.0, "pendente");
        Pedido pedido3 = new Pedido(3L, 6L, "1/2/3456", Arrays.asList(anel.getId(), colar.getId()), 12000.0, "pendente");
        pedidoService.adicionarPedido(pedido1);
        pedidoService.adicionarPedido(pedido2);
        pedidoService.adicionarPedido(pedido3);

        // 5. Processar Pagamentos para os Pedidos
        Pagamento pagamento1 = new Pagamento(1L, 2000.0, LocalDate.now(), "Crédito", 20L);
        Pagamento pagamento2 = new Pagamento(2L, 10200.0, LocalDate.now(), "Dinheiro", 21L);
        Pagamento pagamento3 = new Pagamento(3L, 1050, LocalDate.now(), "Debito", 22L);
        pagamentoService.adicionarPagamento(pagamento1);
        pagamentoService.adicionarPagamento(pagamento2);
        pagamentoService.adicionarPagamento(pagamento3);

        // 6. Atualizar Status dos Pedidos
        pedidoService.atualizarStatusPedido(pedido1.getId(), "entregue");
        pedidoService.atualizarStatusPedido(pedido2.getId(), "aceito");
        pedidoService.atualizarStatusPedido(pedido3.getId(), "cancelado");

        // Exibir Informações (Demonstração)
        System.out.println("=== Funcionários Cadastrados ===");
        funcionarioService.listarFuncionarios().forEach(func ->
                System.out.println(func.getNome() + " - " + "NIF: " + func.getNif()));

        System.out.println("\n=== Joias Cadastradas ===");
        joiaService.listarJoias().forEach(joia ->
                System.out.println(joia.getNome() + " - " + joia.getTipo() + " - R$" + joia.getPreco()));

        System.out.println("\n=== Clientes Cadastrados ===");
        clienteService.listarClientes().forEach(cliente ->
                System.out.println(cliente.getNome() + " - " + cliente.getEmail()));

        System.out.println("\n=== Pedidos Realizados ===");
        pedidoService.listarPedidos().forEach(pedido -> {
            Cliente cliente = clienteService.buscarClientePorId(pedido.getClienteId());
            System.out.println("Pedido ID: " + pedido.getId() + " - Cliente: " + (cliente != null ? cliente.getNome() : "N/A") +
                    " - Valor: R$" + pedido.getValorTotal() + " - Status: " + pedido.getStatus());
        });
    }
}

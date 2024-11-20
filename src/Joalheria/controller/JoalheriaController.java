package Joalheria.controller;

import Joalheria.entity.Cliente;
import Joalheria.entity.Funcionario;
import Joalheria.entity.Joia;
import Joalheria.entity.Pedido;
import Joalheria.service.ClienteService;
import Joalheria.service.FuncionarioService;
import Joalheria.service.JoiaService;
import Joalheria.service.PedidoService;

import java.io.IOException;
import java.util.List;

public class JoalheriaController {
    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final ClienteService clienteService = new ClienteService();
    private final JoiaService joiaService = new JoiaService();
    private final PedidoService pedidoService = new PedidoService();


    // --- Funcionalidades de Funcionários ---
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }
    public Funcionario buscarFuncionarioPorId(int id) {
        return funcionarioService.buscarFuncionarioPorId((long) id);
    }
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarioService.adicionarFuncionario(funcionario);
        System.out.println("Funcionário adicionado: " + funcionario.getNome());
    }


    // --- Funcionalidades de Clientes ---
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }
    public void adicionarCliente(Cliente cliente) {
        clienteService.adicionarCliente(cliente);
        System.out.println("Cliente adicionado: " + cliente.getNome());
    }
    public Cliente buscarClientePorId(int id) {
        return clienteService.buscarClientePorId((long) id);
    }


    // --- Funcionalidades de Joias ---
    public List<Joia> listarTodasJoias() {
        return joiaService.listarJoias();
    }
    public void adicionarJoia(Joia joia) {
        joiaService.adicionarJoia(joia);
        System.out.println("Joia adicionada: " + joia.getNome());
    }
    public Joia buscarJoiaPorId(int id) {
        return joiaService.buscarJoiaPorId((long) id);
    }


    // --- Funcionalidades de Pedidos ---
    public List<Pedido> listarPedidos() {
        try {
            return pedidoService.listarPedidos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
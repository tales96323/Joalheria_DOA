package Joalheria.service;

import Joalheria.entity.Cliente;
import Joalheria.exception.EntityAlreadyExistsException;
import Joalheria.repository.ClienteRepository;

import java.util.List;

public class ClienteService {
    // Dependência do repositório de clientes para manipulação dos dados
    public ClienteRepository clienteRepository;

    // Construtor que inicializa o repositório
    public ClienteService() {
        this.clienteRepository = new ClienteRepository();
    }

    // Metodo para adicionar um novo cliente ao sistema
    public void adicionarCliente(Cliente cliente) {
        try {
            clienteRepository.adicionarCliente(cliente);
            System.out.println("Cliente adicionado com sucesso: " + cliente.getNome());
        } catch (EntityAlreadyExistsException e) {
            System.err.println("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    // Metodo para buscar um cliente pelo ID
    public Cliente buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente com ID " + id + " não encontrado.");
        }
        return cliente;
    }

    // Metodo para listar todos os clientes cadastrados
    public List<Cliente> listarClientes() {
        return clienteRepository.listarClientes();
    }
}
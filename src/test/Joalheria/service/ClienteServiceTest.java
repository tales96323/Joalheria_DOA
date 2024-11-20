package test.Joalheria.service;

import Joalheria.entity.Cliente;
import Joalheria.repository.ClienteRepository;
import Joalheria.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ClienteServiceTest {

    private ClienteService clienteService;
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository = new ClienteRepository();
        clienteService = new ClienteService() {
            {
                this.clienteRepository = clienteRepository;
            }
        };
    }

    @Test
    void testBuscarClientePorId_ClienteExiste() {
        Cliente cliente = new Cliente(1L, "Alice Santos", "11122233344", "alice@email.com", "123456789", "Rua A, 123");
        clienteService.adicionarCliente(cliente);

        Cliente resultado = clienteService.buscarClientePorId(1L);

        assertNotNull(resultado);
        assertEquals("Alice Santos", resultado.getNome());
    }

    @Test
    void testBuscarClientePorId_ClienteNaoExiste() {
        Cliente resultado = clienteService.buscarClientePorId(99L);
        assertNull(resultado, "Cliente com ID inexistente deve retornar null.");
    }

    @Test
    void testListarClientes() {
        Cliente cliente1 = new Cliente(1L, "Alice Santos", "11122233344", "alice@email.com", "123456789", "Rua A, 123");
        Cliente cliente2 = new Cliente(2L, "Bruno Pereira", "55566677788", "bruno@email.com", "987654321", "Rua B, 456");

        clienteService.adicionarCliente(cliente1);
        clienteService.adicionarCliente(cliente2);

        List<Cliente> clientes = clienteService.listarClientes();

        assertEquals(2, clientes.size());
        assertEquals("Alice Santos", clientes.get(0).getNome());
        assertEquals("Bruno Pereira", clientes.get(1).getNome());
    }
}

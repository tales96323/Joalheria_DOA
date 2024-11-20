package test.Joalheria.service;

import Joalheria.entity.Funcionario;
import Joalheria.repository.FuncionarioRepository;
import Joalheria.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class FuncionarioServiceTest {

    private FuncionarioService funcionarioService;
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setUp() {
        funcionarioRepository = new FuncionarioRepository();
        funcionarioService = new FuncionarioService() {
            {
                this.funcionarioRepository = funcionarioRepository;
            }
        };
    }


    @Test
    void testBuscarFuncionarioPorId_FuncionarioExiste() {
        Funcionario gerente = new Funcionario.Gerente(1L, "Joana Silva", "123456789", "2023-01-15", 5000.00, 100000.00, "Gerencia");

        funcionarioService.adicionarFuncionario(gerente);

        Funcionario resultado = funcionarioService.buscarFuncionarioPorId(1L);

        assertNotNull(resultado);
        assertEquals("Joana Silva", resultado.getNome());
    }

    @Test
    void testBuscarFuncionarioPorId_FuncionarioNaoExiste() {
        Funcionario resultado = funcionarioService.buscarFuncionarioPorId(99L);

        assertNull(resultado, "Funcionário com ID inexistente deve retornar null.");
    }

    @Test
    void testListarFuncionarios() {
        Funcionario gerente = new Funcionario.Gerente(1L, "Joana Silva", "123456789", "2023-01-15", 5000.00, 100000.00, "Gerencia");
        Funcionario vendedor = new Funcionario.Vendedor(2L, "Carlos Almeida", "987654321", "2023-02-10", 3000.00, 50000.00, 500);

        funcionarioService.adicionarFuncionario(gerente);
        funcionarioService.adicionarFuncionario(vendedor);

        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();

        assertEquals(2, funcionarios.size());
        assertEquals("Joana Silva", funcionarios.get(0).getNome());
        assertEquals("Carlos Almeida", funcionarios.get(1).getNome());
    }
}

package test.Joalheria.entity;

import Joalheria.entity.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FuncionarioTest {

    private Funcionario funcionarioGerente;
    private Funcionario funcionarioVendedor;

    @BeforeEach
    void setUp() {
        funcionarioGerente = new Funcionario.Gerente(
                1L,
                "Joana Silva",
                "12345678901",
                "2023-01-15",
                5000.00,
                100000.00,
                "Gerência"
        );

        funcionarioVendedor = new Funcionario.Vendedor(
                2L,
                "Carlos Almeida",
                "98765432101",
                "2023-02-10",
                3000.00,
                50000.00,
                20
        );
    }

    @Test
    void testGetId() {
        assertEquals(1L, funcionarioGerente.getId());
        assertEquals(2L, funcionarioVendedor.getId());
    }

    @Test
    void testSetId() {
        funcionarioGerente.setId(10L);
        assertEquals(10L, funcionarioGerente.getId());
    }

    @Test
    void testGetNome() {
        assertEquals("Joana Silva", funcionarioGerente.getNome());
        assertEquals("Carlos Almeida", funcionarioVendedor.getNome());
    }

    @Test
    void testSetNome() {
        funcionarioGerente.setNome("Ana Souza");
        assertEquals("Ana Souza", funcionarioGerente.getNome());
    }

    @Test
    void testGetNif() {
        assertEquals("12345678901", funcionarioGerente.getNif());
    }

    @Test
    void testSetNif() {
        funcionarioGerente.setNif("98765432100");
        assertEquals("98765432100", funcionarioGerente.getNif());
    }

    @Test
    void testGetSalarioBase() {
        assertEquals(5000.00, funcionarioGerente.getSalario());
        assertEquals(3000.00, funcionarioVendedor.getSalario());
    }

    @Test
    void testSetSalarioBase() {
        funcionarioGerente.setSalario(5500.00);
        assertEquals(5500.00, funcionarioGerente.getSalario());
    }


    @Test
    void testVendedorGetComissao() {
        assertTrue(funcionarioVendedor instanceof Funcionario.Vendedor);
        assertEquals(20, ((Funcionario.Vendedor) funcionarioVendedor).getComissao());
    }

    @Test
    void testVendedorSetComissao() {
        ((Funcionario.Vendedor) funcionarioVendedor).setComissao(25);
        assertEquals(25, ((Funcionario.Vendedor) funcionarioVendedor).getComissao());
    }
}

package test.Joalheria.entity;

import Joalheria.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(
                1L,
                "Alice Santos",
                "11122233344",
                "alice@email.com",
                "123456789",
                "Rua A, 123"
        );
    }

    @Test
    void testGetId() {
        assertEquals(1L, cliente.getId());
    }

    @Test
    void testSetId() {
        cliente.setId(10L);
        assertEquals(10L, cliente.getId());
    }

    @Test
    void testGetNome() {
        assertEquals("Alice Santos", cliente.getNome());
    }

    @Test
    void testSetNome() {
        cliente.setNome("Bruno Pereira");
        assertEquals("Bruno Pereira", cliente.getNome());
    }

    @Test
    void testGetCpf() {
        assertEquals("11122233344", cliente.getNif());
    }

    @Test
    void testSetCpf() {
        cliente.setNif("99988877766");
        assertEquals("99988877766", cliente.getNif());
    }

    @Test
    void testGetEmail() {
        assertEquals("alice@email.com", cliente.getEmail());
    }

    @Test
    void testSetEmail() {
        cliente.setEmail("bruno@email.com");
        assertEquals("bruno@email.com", cliente.getEmail());
    }

    @Test
    void testGetTelefone() {
        assertEquals("123456789", cliente.getTelefone());
    }

    @Test
    void testSetTelefone() {
        cliente.setTelefone("987654321");
        assertEquals("987654321", cliente.getTelefone());
    }

    @Test
    void testGetEndereco() {
        assertEquals("Rua A, 123", cliente.getEndereco());
    }

    @Test
    void testSetEndereco() {
        cliente.setEndereco("Rua B, 456");
        assertEquals("Rua B, 456", cliente.getEndereco());
    }
}

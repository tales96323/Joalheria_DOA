package test.Joalheria.entity;

import Joalheria.entity.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PedidoTest {

    private Pedido pedido;
    private List<Long> joias;

    @BeforeEach
    void setUp() {
        joias = Arrays.asList(1L, 2L, 3L);
        pedido = new Pedido(
                1L,
                10L,
                "2023-11-15",
                joias,
                5000.0,
                "pendente"
        );
    }

    @Test
    void testGetId() {
        assertEquals(1L, pedido.getId());
    }

    @Test
    void testSetId() {
        pedido.setId(2L);
        assertEquals(2L, pedido.getId());
    }

    @Test
    void testGetClienteId() {
        assertEquals(10L, pedido.getClienteId());
    }

    @Test
    void testSetClienteId() {
        pedido.setClienteId(20L);
        assertEquals(20L, pedido.getClienteId());
    }

    @Test
    void testGetValorTotal() {
        assertEquals(5000.0, pedido.getValorTotal());
    }

    @Test
    void testSetValorTotal() {
        pedido.setValorTotal(6000.0);
        assertEquals(6000.0, pedido.getValorTotal());
    }

    @Test
    void testGetStatus() {
        assertEquals("pendente", pedido.getStatus());
    }

    @Test
    void testSetStatus() {
        pedido.setStatus("entregue");
        assertEquals("entregue", pedido.getStatus());
    }
}

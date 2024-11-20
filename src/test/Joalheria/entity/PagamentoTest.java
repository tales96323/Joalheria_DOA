package test.Joalheria.entity;

import Joalheria.entity.Pagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class PagamentoTest {

    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        pagamento = new Pagamento(
                1L,
                2000.0,
                LocalDate.of(2023, 11, 15),
                "Cartão de Crédito",
                10L
        );
    }

    @Test
    void testGetId() {
        assertEquals(1L, pagamento.getId());
    }

    @Test
    void testSetId() {
        pagamento.setId(2L);
        assertEquals(2L, pagamento.getId());
    }

    @Test
    void testGetValor() {
        assertEquals(2000.0, pagamento.getValor());
    }

    @Test
    void testSetValor() {
        pagamento.setValor(2500.0);
        assertEquals(2500.0, pagamento.getValor());
    }


    @Test
    void testGetMetodoPagamento() {
        assertEquals("Cartão de Crédito", pagamento.getMetodoPagamento());
    }

    @Test
    void testSetMetodoPagamento() {
        pagamento.setMetodoPagamento("Pix");
        assertEquals("Pix", pagamento.getMetodoPagamento());
    }

    @Test
    void testGetPedidoId() {
        assertEquals(10L, pagamento.getPedidoId());
    }

    @Test
    void testSetPedidoId() {
        pagamento.setPedidoId(20L);
        assertEquals(20L, pagamento.getPedidoId());
    }
}

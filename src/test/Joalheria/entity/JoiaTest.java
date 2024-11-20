package test.Joalheria.entity;

import Joalheria.entity.Joia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JoiaTest {

    @Test
    void testCriarJoia() {
        Joia joia = new Joia(1L, "Anel de Ouro", "Anel", "Ouro", 5.0, 10000.0, 10, "Luxo");
        assertNotNull(joia);
        assertEquals("Anel de Ouro", joia.getNome());
    }
}
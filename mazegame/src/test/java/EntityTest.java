import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cmpt276.Entity;

public class EntityTest {
    private Entity curEntity;

    @BeforeEach
    void setup() {
        curEntity = new Entity();
    }

    @Test
    void testDefaultGetSanityImpact() {
        assertEquals(curEntity.getSanityImpact(), 0);
    }

    @Test
    void testSetSanityImpact() {
        curEntity.setSanityImpact(100);
        assertEquals(curEntity.getSanityImpact(), 100);
    }
}

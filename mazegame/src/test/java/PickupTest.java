import org.junit.jupiter.api.Test;

import com.cmpt276.GameObjectType;
import com.cmpt276.Pickup;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class PickupTest {
    private Pickup pickup;
    private int x = 50;
    private int y = 75;
    private int sanityImpact = 5;

    @BeforeEach
    void setup() {
        pickup = new Pickup(x, y, sanityImpact);
    }

    @Test
    void verifyXPosition() {
        assertEquals(pickup.getX(), x);
    }

    @Test
    void verifyYPosition() {
        assertEquals(pickup.getY(), y);
    }

    @Test
    void verifyObjectType() {
        assertEquals(pickup.getGoType(), GameObjectType.Pickup);
    }

    @Test
    void verifySprite() {
        assertEquals(pickup.getSprite(),
                Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/coffee_smaller.png")));
    }
}

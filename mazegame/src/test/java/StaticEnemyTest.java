import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.cmpt276.GameObjectType;
import com.cmpt276.StaticEnemy;
import java.awt.*;

public class StaticEnemyTest {
    private StaticEnemy enemy;
    private int x = 50;
    private int y = 75;
    private int sanityImpact = 5;

    @BeforeEach
    void setup() {
        enemy = new StaticEnemy(x, y, sanityImpact);
    }

    @Test
    void verifyXPosition() {
        assertEquals(enemy.getX(), x);
    }

    @Test
    void verifyYPosition() {
        assertEquals(enemy.getY(), y);
    }

    @Test
    void verifyObjectType() {
        assertEquals(enemy.getGoType(), GameObjectType.StaticEnemy);
    }

    @Test
    void verifySprite() {
        assertEquals(enemy.getSprite(),
                Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/Paper.png")));
    }

}

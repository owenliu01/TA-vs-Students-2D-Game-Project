import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import com.cmpt276.GameObject;
import com.cmpt276.GameObjectType;

public class GameObjectTest {

    private GameObject gameObject;

    // Set testing object paramters
    private int xPos = 10;
    private int yPos = 20;

    @BeforeEach
    void setup() {
        gameObject = new GameObject(xPos, yPos);
    }

    @Test
    void getPos() {
        assertEquals(gameObject.getX(), xPos);
        assertEquals(gameObject.getY(), yPos);
        assertEquals(gameObject.getLocation()[0], xPos);
        assertEquals(gameObject.getLocation()[1], yPos);
    }

    @Test
    void setPos() {

        // Set x y parameter
        final int newX = 78;
        final int newY = 54;

        final int negNewX = -14;
        final int negNewY = -82;

        // Test for Positive case
        gameObject.setX(newX);
        gameObject.setY(newY);
        assertEquals(gameObject.getX(), newX);
        assertEquals(gameObject.getY(), newY);

        // Test for Negative case
        gameObject.setX(negNewX);
        gameObject.setY(negNewY);
        assertEquals(gameObject.getX(), negNewX);
        assertEquals(gameObject.getY(), negNewY);
    }

    @Test
    void getType() {
        gameObject.setGoType(GameObjectType.Barrier);
        assertEquals(gameObject.getGoType(), GameObjectType.Barrier);
    }
}
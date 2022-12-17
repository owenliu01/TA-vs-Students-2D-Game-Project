import org.junit.jupiter.api.Test;

import com.cmpt276.Barrier;
import com.cmpt276.BarrierType;
import com.cmpt276.GameObjectType;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class BarrierTest {
    private Barrier barrier;
    private int x = 50;
    private int y = 75;

    @BeforeEach
    void setup() {
        barrier = new Barrier(x, y, BarrierType.Wall);
    }

    @Test
    void testGetGoType() {
        assertEquals(barrier.getGoType(), GameObjectType.Barrier);
    }

    @Test
    void testBarrierType() {
        assertEquals(barrier.getBarrierType(), BarrierType.Wall);
    }

    @Test
    void testWallSprite() {
        assertEquals(barrier.getSprite(),
                Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/block.png")));
    }

    @Test
    void testBookshelfSprite() {
        Barrier bookShelf = new Barrier(x, y, BarrierType.Bookshelf);
        assertEquals(bookShelf.getSprite(),
                Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/Bookshelf.png")));
    }

    @Test
    void testFloorSignSprite() {
        Barrier floorSign = new Barrier(x, y, BarrierType.FloorSign);
        assertEquals(floorSign.getSprite(),
                Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/FloorSign.png")));
    }

    @Test
    void testGetX() {
        assertEquals(barrier.getX(), x);
    }

    @Test
    void testGetY() {
        assertEquals(barrier.getY(), y);
    }
}

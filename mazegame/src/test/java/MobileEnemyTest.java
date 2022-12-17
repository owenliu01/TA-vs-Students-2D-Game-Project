import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import com.cmpt276.MobileEnemy;
import com.cmpt276.Player;

public class MobileEnemyTest {
    private Player p;
    private MobileEnemy e;
    private int expectedXLocation;
    private int expectedYLocation;

    @BeforeEach
    void setup() {
        p = new Player();
    }

    @Test
    void testMoveToLeftOfPlayer(){
        //Move x location towards player when player is to the left of enemy
        e = new MobileEnemy(50, p.getY());
        expectedXLocation = (e.getX() - e.getMoveSpeed());
        e.moveToPlayer(p);
        assertEquals(e.getX(),expectedXLocation);
    }

    @Test
    void testMoveToRightOfPlayer(){
        //Move x location towards player when player is to the right of enemy
        e = new MobileEnemy(50, p.getY());
        p.setX(60);
        expectedXLocation = (e.getX() + e.getMoveSpeed());
        e.moveToPlayer(p);
        assertEquals(e.getX(),expectedXLocation);
    }

    @Test
    void testMoveToPlayerBelow(){
        //Move Y location towards player when player above the enemy
        e = new MobileEnemy(50, 580);
        p.setX(50);
        p.setY(540);
        expectedYLocation = (e.getY() - e.getMoveSpeed());
        e.moveToPlayer(p);
        assertEquals(e.getY(),expectedYLocation);
    }

    @Test
    void testMoveToPlayerAbove(){
        //Move Y location towards player when player above the enemy
        e = new MobileEnemy(50, 540);
        p.setX(50);
        p.setY(580);
        expectedYLocation = (e.getY() + e.getMoveSpeed());
        e.moveToPlayer(p);
        assertEquals(e.getY(),expectedYLocation);
    }
}

import com.cmpt276.gamePanel;
import com.cmpt276.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

public class GamePanelTest {
    private Player p;

    @BeforeEach
    void setup() {
        gamePanel.resetGameAttributes();
        p = gamePanel.getMainPlayer();
    }

    private int[] badLocation = {-1, -1};
    private int[] validLocation = {0, 0};
    private int[] checkeeDimensions = {100, 100};
    @Test
    void checkNoBarrier_outOfBounds() {
        Rectangle badRectangle = new Rectangle(badLocation[0], badLocation[1], checkeeDimensions[0], checkeeDimensions[1]);
        boolean ret = gamePanel.checkNoBarrier(badRectangle, true);
        assertFalse(ret);
    }

    @Test
    void checkNoBarrier_noCollision() {
        //do not spawn any barriers; thus this should always say no collision
        Rectangle validRectangle = new Rectangle(validLocation[0], validLocation[1], checkeeDimensions[0], checkeeDimensions[1]);
        boolean ret = gamePanel.checkNoBarrier(validRectangle, true);
        assertTrue(ret);
    }

    @Test
    void checkNoBarrier_collision() {
        //spawn barriers, then check behaviour if the checkee intersects with a boundary wall
        gamePanel.createWallBoundary();
        Rectangle validRectangle = new Rectangle(validLocation[0], validLocation[1], checkeeDimensions[0], checkeeDimensions[1]);
        boolean ret = gamePanel.checkNoBarrier(validRectangle, true);
        assertFalse(ret);
    }

    @Test
    void checkCollision_noCollisions() {
        //do not instantiate any stage features, so no collision is possible
        int startingSanity = p.getSanity();

        boolean ret = gamePanel.checkCollision(true);
        assertTrue(ret);

        assertTrue(gamePanel.isGameRunning());
        assertEquals(startingSanity, p.getSanity());
    }

    private int[] mobileEnemyLocation = {335, 270};
    @Test
    void checkCollision_collideWithMobileEnemy() {
        gamePanel.createMobileEnemies();
        int startingSanity = p.getSanity();

        //put the player on a mobile enemy
        p.setX(mobileEnemyLocation[0]);
        p.setY(mobileEnemyLocation[1]);
        boolean ret = gamePanel.checkCollision(true);
        assertTrue(ret);

        boolean gameRunning = gamePanel.isGameRunning();
        assertFalse(gameRunning);
        assertEquals(startingSanity, p.getSanity());
    }

    private int[] staticEnemyLocation = {510, 45};
    private int staticEnemySanityImpact = -15;
    @Test
    void checkCollision_collideWithStaticEnemy() {
        gamePanel DUT = null;
        gamePanel.createStaticEnemies();
        int startingSanity = p.getSanity();

        //put the player on a static enemy
        p.setX(staticEnemyLocation[0]);
        p.setY(staticEnemyLocation[1]);
        boolean ret = DUT.checkCollision(true);
        assertTrue(ret);

        int expectedSanity = startingSanity + staticEnemySanityImpact;
        assertEquals(expectedSanity, p.getSanity());
        assertTrue(gamePanel.isGameRunning());
    }

    private int[] normalPickupLocation = {500, 140};
    private int normalPickupSanityImpact = 5;
    @Test
    void checkCollision_collideWithNormalPickup() {
        gamePanel.createNormalPickups();
        int startingSanity = p.getSanity();

        //put the player on a normal pickup
        p.setX(normalPickupLocation[0]);
        p.setY(normalPickupLocation[1]);
        boolean ret = gamePanel.checkCollision(true);
        assertTrue(ret);

        int expectedSanity = startingSanity + normalPickupSanityImpact;
        assertEquals(expectedSanity, p.getSanity());
        assertTrue(gamePanel.isGameRunning());
    }

    private int[] specialPickupLocation = {100, 100};
    private int specialPickupSanityImpact = 10;
    @Test
    void checkCollision_collideWithSpecialPickup() {
        gamePanel.addCoordsToSpecialPickup(specialPickupLocation[0], specialPickupLocation[1]);
        gamePanel.createSpecialPickups();
        int startingSanity = p.getSanity();

        //put the player on the special pickup
        p.setX(specialPickupLocation[0]);
        p.setY(specialPickupLocation[1]);
        boolean ret = gamePanel.checkCollision(true);
        assertTrue(ret);

        int expectedSanity = startingSanity + specialPickupSanityImpact;
        assertEquals(expectedSanity, p.getSanity());
        assertTrue(gamePanel.isGameRunning());
    }

    @Test
    void noOverlappingEntities_noOverlap() {
        //do not instantiate any other mobileEnemies, then test
        boolean ret = gamePanel.noOverlappingEntities(mobileEnemyLocation, checkeeDimensions, null, true);
        assertTrue(ret);
    }

    @Test
    void noOverlappingEntities_yesOverlap() {
        gamePanel.createMobileEnemies();
        boolean ret = gamePanel.noOverlappingEntities(mobileEnemyLocation, checkeeDimensions, null, true);
        assertFalse(ret);
    }
}

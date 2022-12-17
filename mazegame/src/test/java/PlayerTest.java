import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cmpt276.Player;

public class PlayerTest {
    private Player curPlayer;
    private int defaultSanityValue = 50;

    @BeforeEach
    void setup() {
        curPlayer = new Player();
    }

    @Test
    void testGetPickupsCollected() {
        assertEquals(curPlayer.getPickupsCollected(), 0);
    }

    @Test
    void testGetSanity() {
        assertEquals(curPlayer.getSanity(), defaultSanityValue);
    }

    @Test
    void testGetSanityAfterChange() {
        curPlayer.updateSanity(5);
        assertEquals(curPlayer.getSanity(), defaultSanityValue + 5);
    }

    @Test
    void testGetSpecialPickupsCollected() {
        assertEquals(curPlayer.getSpecialPickupsCollected(), 0);
    }

    @Test
    void testIncrementPickup() {
        curPlayer.incrementPickup();
        assertEquals(curPlayer.getPickupsCollected(), 1);
    }

    @Test
    void testIncrementSpecialPickup() {
        curPlayer.incrementSpecialPickup();
        assertEquals(curPlayer.getSpecialPickupsCollected(), 1);
    }

    @Test
    void testUpdateSanity() {
        curPlayer.updateSanity(15);
        assertEquals(curPlayer.getSanity(), defaultSanityValue + 15);
    }
}

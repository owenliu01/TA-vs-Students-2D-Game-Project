import com.cmpt276.Controller;
import com.cmpt276.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ControllerTest {
    private Controller DUT; // device under test
    private Player mainPlayer;
    private final int playerSpeed = 5; // this must match the value in controller.java
    private final int topBound = 0;
    private final int bottomBound = 615;
    private final int leftBound = 0;
    private final int rightBound = 770;

    @BeforeEach
    void setup() {
        mainPlayer = new Player();
        mainPlayer.setX(300);
        mainPlayer.setY(300);
        DUT = new Controller(mainPlayer);
    }

    @Test
    void keyPressed_upInput() {
        assertFalse(DUT.isKeyUp());

        // send in an up arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertTrue(DUT.isKeyUp());
    }

    @Test
    void keyPressed_keyReleased_upInput() {
        assertFalse(DUT.isKeyUp());

        // send in an up arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertTrue(DUT.isKeyUp());

        // release the up key
        DUT.keyReleased(KE);

        assertFalse(DUT.isKeyUp());
    }

    @Test
    void keyPressed_keyReleased_keyProcess_upInput_inBounds() {
        assertFalse(DUT.isKeyUp());

        int playerStartLocation[] = mainPlayer.getLocation();

        // send in an up arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);
        DUT.keyProcess();

        int playerExpectedLocation[] = { playerStartLocation[0], playerStartLocation[1] - playerSpeed };
        int playerActualLocation[] = mainPlayer.getLocation();

        assertArrayEquals(playerExpectedLocation, playerActualLocation);
    }

    @Test
    void keyPressed_keyReleased_keyProcess_upInput_outOfBounds() {
        assertFalse(DUT.isKeyUp());

        mainPlayer.setY(topBound);

        int playerStartLocation[] = mainPlayer.getLocation();

        // send in an up arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);
        DUT.keyProcess();

        int playerExpectedLocation[] = { playerStartLocation[0], playerStartLocation[1] };
        int playerActualLocation[] = mainPlayer.getLocation();

        assertArrayEquals(playerExpectedLocation, playerActualLocation);
    }

    @Test
    void keyPressed_downInput() {
        assertFalse(DUT.isKeyDown());

        // send in a down arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertTrue(DUT.isKeyDown());
    }

    @Test
    void keyPressed_keyReleased_downInput() {
        assertFalse(DUT.isKeyDown());

        // send in a down arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertTrue(DUT.isKeyDown());

        // release the down key
        DUT.keyReleased(KE);

        assertFalse(DUT.isKeyDown());
    }

    @Test
    void keyPressed_keyReleased_keyProcess_downInput_inBounds() {
        assertFalse(DUT.isKeyDown());

        int playerStartLocation[] = mainPlayer.getLocation();

        // send in a down arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);
        DUT.keyProcess();

        assertTrue(DUT.isKeyDown());

        int playerExpectedLocation[] = { playerStartLocation[0], playerStartLocation[1] + playerSpeed };
        int playerActualLocation[] = mainPlayer.getLocation();

        assertArrayEquals(playerExpectedLocation, playerActualLocation);
    }

    @Test
    void keyPressed_keyReleased_keyProcess_downInput_outOfBounds() {
        assertFalse(DUT.isKeyDown());

        mainPlayer.setY(bottomBound);

        int playerStartLocation[] = mainPlayer.getLocation();

        // send in a down arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);
        DUT.keyProcess();

        assertTrue(DUT.isKeyDown());

        int playerExpectedLocation[] = { playerStartLocation[0], playerStartLocation[1] };
        int playerActualLocation[] = mainPlayer.getLocation();

        assertArrayEquals(playerExpectedLocation, playerActualLocation);
    }

    @Test
    void keyPressed_leftInput() {
        assertFalse(DUT.isKeyLeft());

        // send in a left arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertTrue(DUT.isKeyLeft());
    }

    @Test
    void keyPressed_keyReleased_leftInput() {
        assertFalse(DUT.isKeyLeft());

        // send in a left arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertTrue(DUT.isKeyLeft());

        // release the left key
        DUT.keyReleased(KE);

        assertFalse(DUT.isKeyLeft());
    }

    @Test
    void keyPressed_keyReleased_keyProcess_leftInput_inBounds() {
        assertFalse(DUT.isKeyLeft());

        int playerStartLocation[] = mainPlayer.getLocation();

        // send in a left arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);
        DUT.keyProcess();

        int playerExpectedLocation[] = { playerStartLocation[0] - playerSpeed, playerStartLocation[1] };
        int playerActualLocation[] = mainPlayer.getLocation();

        assertArrayEquals(playerExpectedLocation, playerActualLocation);
    }

    @Test
    void keyPressed_keyReleased_keyProcess_leftInput_outOfBounds() {
        assertFalse(DUT.isKeyLeft());

        mainPlayer.setX(leftBound);

        int playerStartLocation[] = mainPlayer.getLocation();

        // send in a left arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);
        DUT.keyProcess();

        int playerExpectedLocation[] = { playerStartLocation[0], playerStartLocation[1] };
        int playerActualLocation[] = mainPlayer.getLocation();

        assertArrayEquals(playerExpectedLocation, playerActualLocation);
    }

    @Test
    void keyPressed_rightInput() {
        assertFalse(DUT.isKeyRight());

        // send in a right arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertTrue(DUT.isKeyRight());
    }

    @Test
    void keyPressed_keyReleased_rightInput() {
        assertFalse(DUT.isKeyRight());

        // send in a right arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertTrue(DUT.isKeyRight());

        // release the right key
        DUT.keyReleased(KE);

        assertFalse(DUT.isKeyRight());
    }

    @Test
    void keyPressed_keyReleased_keyProcess_rightInput_inBounds() {
        assertFalse(DUT.isKeyRight());

        int playerStartLocation[] = mainPlayer.getLocation();

        // send in a right arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);
        DUT.keyProcess();

        int playerExpectedLocation[] = { playerStartLocation[0] + playerSpeed, playerStartLocation[1] };
        int playerActualLocation[] = mainPlayer.getLocation();

        assertArrayEquals(playerExpectedLocation, playerActualLocation);
    }

    @Test
    void keyPressed_keyReleased_keyProcess_rightInput_outOfBounds() {
        assertFalse(DUT.isKeyRight());

        mainPlayer.setX(rightBound);

        int playerStartLocation[] = mainPlayer.getLocation();

        // send in a right arrow input
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);
        DUT.keyProcess();

        int playerExpectedLocation[] = { playerStartLocation[0], playerStartLocation[1] };
        int playerActualLocation[] = mainPlayer.getLocation();

        assertArrayEquals(playerExpectedLocation, playerActualLocation);
    }

    @Test
    void keyPressed_nonArrowInput() {
        assertFalse(DUT.isKeyUp());
        assertFalse(DUT.isKeyDown());
        assertFalse(DUT.isKeyLeft());
        assertFalse(DUT.isKeyRight());

        // send in a U, which isn't an UP, DOWN, LEFT, or RIGHT
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_U, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertFalse(DUT.isKeyUp());
        assertFalse(DUT.isKeyDown());
        assertFalse(DUT.isKeyLeft());
        assertFalse(DUT.isKeyRight());
    }

    @Test
    void keyPressed_keyReleased_nonArrowInput() {
        assertFalse(DUT.isKeyUp());
        assertFalse(DUT.isKeyDown());
        assertFalse(DUT.isKeyLeft());
        assertFalse(DUT.isKeyRight());

        // send in a U, which isn't an UP, DOWN, LEFT, or RIGHT
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_U, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);

        assertFalse(DUT.isKeyUp());
        assertFalse(DUT.isKeyDown());
        assertFalse(DUT.isKeyLeft());
        assertFalse(DUT.isKeyRight());

        DUT.keyReleased(KE);

        assertFalse(DUT.isKeyUp());
        assertFalse(DUT.isKeyDown());
        assertFalse(DUT.isKeyLeft());
        assertFalse(DUT.isKeyRight());
    }

    @Test
    void keyPressed_keyReleased_keyProcess_nonArrowInput() {
        // start by sending in an up arrow input bc otherwise we're already at the
        // bottom bound
        KeyEvent upKE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(upKE);
        DUT.keyProcess();
        DUT.keyReleased(upKE);

        int playerStartLocation[] = mainPlayer.getLocation();

        // send in a U, which isn't an UP, DOWN, LEFT, or RIGHT
        KeyEvent KE = new KeyEvent(new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_U, KeyEvent.CHAR_UNDEFINED);
        DUT.keyPressed(KE);
        DUT.keyProcess();

        int playerExpectedLocation[] = { playerStartLocation[0], playerStartLocation[1] };
        int playerActualLocation[] = mainPlayer.getLocation();

        assertArrayEquals(playerExpectedLocation, playerActualLocation);
    }
}

import org.junit.jupiter.api.Test;

import com.cmpt276.gameFrame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;

public class gameFrameTest {
    private gameFrame frame;
    private String title = "Instructor vs Students";

    @BeforeEach
    void setup() {
        frame = new gameFrame();
    }

    @Test
    void testGetDefaultCloseOperation() {
        assertEquals(frame.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
    }

    @Test
    void testGetTitle() {
        assertEquals(frame.getTitle(), title);
    }

    @Test
    void testIsVisible() {
        assertTrue(frame.isVisible());
    }

    @Test
    void testIsResizable() {
        assertFalse(frame.isResizable());
    }
}

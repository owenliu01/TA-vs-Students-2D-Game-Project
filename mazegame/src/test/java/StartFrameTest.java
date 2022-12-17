import org.junit.jupiter.api.Test;

import com.cmpt276.startFrame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;
import java.awt.*;
import org.junit.jupiter.api.BeforeEach;

public class StartFrameTest {
    private startFrame frame;
    private String title = "Instructor vs Students";

    @BeforeEach
    void setup() {
        frame = new startFrame();
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
    void testIsResizable() {
        assertFalse(frame.isResizable());
    }

    @Test
    void testGetSize() {
        Dimension d = new Dimension(800, 650);
        assertEquals(frame.getSize(), d.getSize());
    }

    @Test
    void testIsVisible() {
        assertTrue(frame.isVisible());
    }
}

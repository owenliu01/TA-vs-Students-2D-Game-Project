import com.cmpt276.ExitTile;
import com.cmpt276.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;
import java.awt.image.ImageObserver;

public class ExitTileTest {
    private ExitTile DUT;
    private Player mainPlayer;
    private ImageObserver playerObserver;
    private ImageObserver tileObserver;
    private boolean canViewPlayerImg = false;
    private boolean canViewTileImg = false;

    @BeforeEach
    void setup() {
        DUT = new ExitTile(0, 0);
        mainPlayer = new Player();
        playerObserver = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                // System.out.println("playerObserver: player image ready to view!");
                canViewPlayerImg = true;
                return false;
            }
        };
        tileObserver = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                // System.out.println("tileObserver: tile image ready to view!");
                canViewTileImg = true;
                return false;
            }
        };

        int playerDimensions[] = mainPlayer.getDimensions(playerObserver);
        int tileDimensions[] = DUT.getDimensions(tileObserver);

        if (playerDimensions[0] != -1 && playerDimensions[1] != -1) {
            // System.out.println("Player image ready to view!");
            canViewPlayerImg = true;
        }

        if (tileDimensions[0] != -1 && tileDimensions[1] != -1) {
            // System.out.println("Tile image ready to view!");
            canViewTileImg = true;
        }

        boolean canViewBothImages = canViewPlayerImg && canViewTileImg;
        while (!canViewBothImages) {
            // do nothing and wait til observers are called to update the images
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                System.out.println("thread.sleep interrupted in exitTileTest");
            }
            canViewBothImages = canViewPlayerImg && canViewTileImg;
        }
        // once we exit, both are ready
    }

    @Test
    void checkForFinish_notOnTileNotEnoughPickups() {
        // check for case where can't win
        boolean canFinish = DUT.checkForFinish(mainPlayer, 1);
        assertFalse(canFinish);
    }

    @Test
    void checkForFinish_notOnTileEnoughPickups() {
        // check for case where can win but not on tile
        mainPlayer.incrementPickup();
        boolean canFinish = DUT.checkForFinish(mainPlayer, 1);
        assertFalse(canFinish);
    }

    @Test
    void checkForFinish_onTileNotEnoughPickups() {
        // check for case where on tile but not enough pickups
        mainPlayer.setX(0);
        mainPlayer.setY(0);

        boolean canFinish = DUT.checkForFinish(mainPlayer, 1);
        assertFalse(canFinish);
    }

    @Test
    void checkForFinish_onTileEnoughPickups() {
        // check for case where on tile and enough pickups
        mainPlayer.setX(0);
        mainPlayer.setY(0);

        mainPlayer.incrementPickup();
        assertEquals(mainPlayer.getPickupsCollected(), 1);

        boolean canFinish = DUT.checkForFinish(mainPlayer, 1);
        assertTrue(canFinish);
    }
}

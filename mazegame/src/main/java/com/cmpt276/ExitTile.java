package com.cmpt276;

import java.awt.*;

/**
 * Class to represent the tile that the player may step on to win the game
 */
public class ExitTile extends Entity {
    /**
     * Creates an ExitTile at the specified location.
     *
     * @param xPos the x coordinate for this object
     * @param yPos the y coordinate for this object
     */
    public ExitTile(int xPos, int yPos) {
        super();
        x = xPos;
        y = yPos;
        goType = GameObjectType.ExitTile;
        this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/finish_flag_v3.png"));
    }

    /**
     * Checks if the player meets all requirements to win the game, and returns a
     * boolean indicating results of this check.
     * Conditions for victory are: collection of all pickups and standing on the
     * exit tile.
     * Should be called every frame for proper functionality.
     * 
     * @param player          the Player object whose victory is being verified
     * @param numPickupsToWin the number of Pickups the Player is required to have
     *                        obtained to win
     * @return boolean true if the player passes requirements to win; false
     *         otherwise
     */
    public boolean checkForFinish(Player player, int numPickupsToWin) {
        Rectangle playerLocation = new Rectangle(player.getX(), player.getY(), player.getDimensions()[0],
                player.getDimensions()[1]);
        Rectangle thisLocation = new Rectangle(this.getX(), this.getY(), this.getDimensions()[0],
                this.getDimensions()[1]);
        if (!playerLocation.intersects(thisLocation)) {
            return false; // cannot finish because the player isn't touching the exit tile
        } else {
            if (player.getPickupsCollected() >= numPickupsToWin) {
                return true; // player has at least as many pickups as needed to win and is on the tile
            } else {
                return false; // player is on tile but doesn't have enough pickups :(
            }
        }
    }
}

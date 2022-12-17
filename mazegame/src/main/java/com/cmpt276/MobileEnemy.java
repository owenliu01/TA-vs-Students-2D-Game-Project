package com.cmpt276;

import java.awt.*;

/**
 * Class to represent students chasing down the Player
 */
public class MobileEnemy extends Entity {
    private int moveSpeed = 2;

    /**
     * Creates a new MobileEnemy at the specified location.
     *
     * @param x the x coordinate for this object
     * @param y the y coordinate for this object
     */
    public MobileEnemy(int x, int y) {
        super(x, y);
        this.sprite = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/Student.png"));
        goType = GameObjectType.MobileEnemy;
    }

    /**
     * Function to return Mobile Enemies Move speed
     * @return moveSpeed var of Mobile Enemy Object
     */
    public int getMoveSpeed(){
        return moveSpeed;
    }

    /**
     * Moves this MobileEnemy in the direction of the given _player parameter. This
     * object will move in one of either
     * the x or y direction; it will pick whichever of the two would make it closest
     * to the target player.
     * If there is a StaticEnemy, Wall, or another MobileEnemy on the tile that this
     * object would move to, no action
     * is taken.
     * 
     * @param _player the player that this enemy will move towards
     */
    public void moveToPlayer(Player _player) {
        int[] ownCurrentLocation = this.getLocation();
        int[] playersLocation = _player.getLocation();
        int[] directionToPlayer = new int[] {
                playersLocation[0] - ownCurrentLocation[0], // x
                playersLocation[1] - ownCurrentLocation[1] // y
        };
        // move in target direction
        // find which direction needs to be moved in more
        int[] spriteSize = this.getDimensions();
        if (Math.abs(directionToPlayer[0]) > Math.abs(directionToPlayer[1])) { // move in x direction
            int newPosition; // proposed x position
            if (directionToPlayer[0] > 0) {
                newPosition = this.getX() + moveSpeed;
            } else {
                newPosition = this.getX() - moveSpeed;
            }
            //if (gamePanel.checkNoBarrier(new int[] { newPosition, this.getY() }, this.getDimensions(), false)
            if (gamePanel.checkNoBarrier(new Rectangle(newPosition, this.getY(), spriteSize[0], spriteSize[1]), false)
                    && gamePanel.noOverlappingEntities(new int[] { newPosition, this.getY() }, this.getDimensions(),
                            this, false)) {
                this.setX(newPosition);
            }

        } else { // move in y direction
            int newPosition; // proposed y position
            if (directionToPlayer[1] > 0) {
                newPosition = this.getY() + moveSpeed;
            } else {
                newPosition = this.getY() - moveSpeed;
            }
            //if (gamePanel.checkNoBarrier(new int[] { this.getX(), newPosition }, this.getDimensions(), false)
            if (gamePanel.checkNoBarrier(new Rectangle(this.getX(), newPosition, spriteSize[0], spriteSize[1]), false)
                    && gamePanel.noOverlappingEntities(new int[] { this.getX(), newPosition }, this.getDimensions(),
                            this, false)) {
                this.setY(newPosition);
            }
        }
    }

}

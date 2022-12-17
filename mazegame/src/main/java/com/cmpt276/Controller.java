package com.cmpt276;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class to handle inputs for the Player object
 */
public class Controller extends KeyAdapter {
    boolean keyUp = false;
    boolean keyDown = false;
    boolean keyLeft = false;
    boolean keyRight = false;
    Player mainPlayer;

    /**
     * The minimum legal y coordinate of mobile entities
     */
    protected static int topBound = 0;
    /**
     * The maximum legal y coordinate of mobile entities
     */
    protected static int bottomBound = 615;
    /**
     * The minimum legal x coordinate of mobile entities
     */
    protected static int leftBound = 0;
    /**
     * The maximum legal x coordinate of mobile entities
     */
    protected static int rightBound = 770;

    private static int speed = 5;

    /**
     * Creates a Controller for the specified Player object.
     *
     * @param mainPlayer the Player to control with this object
     */
    public Controller(Player mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    /**
     * Handles key inputs for the player object.
     * Uses standard arrow key inputs.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyUp = true;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = true;
                break;
            case KeyEvent.VK_LEFT:
                keyLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                keyRight = true;
                break;
        }
    }

    /**
     * Handles releasing of keys for the player.
     * Uses standard arrow key inputs.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyUp = false;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = false;
                break;
            case KeyEvent.VK_LEFT:
                keyLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                keyRight = false;
                break;
        }
    }

    /**
     * Check if there is no barrier (i.e. Wall) present where the character is about
     * to move.
     * Once the character moves, check if there is a collision with an enemy or
     * pickup.
     */
    public void keyProcess() {
        int[] currLocation = mainPlayer.getLocation();
        int[] playerSize = mainPlayer.getDimensions();
        if (keyUp) {
            int newPosition = mainPlayer.getY() - speed;
            //if (gamePanel.checkNoBarrier(new int[] { currLocation[0], newPosition }, mainPlayer.getDimensions(), false)) {
            if (gamePanel.checkNoBarrier(new Rectangle(currLocation[0],newPosition, playerSize[0],playerSize[1]), false)) {
                mainPlayer.setY(newPosition);
            }
        }
        if (keyDown) {
            int newPosition = mainPlayer.getY() + speed;
            //if (gamePanel.checkNoBarrier(new int[] { currLocation[0], newPosition }, mainPlayer.getDimensions(), false)) {
            if (gamePanel.checkNoBarrier(new Rectangle(currLocation[0], newPosition, playerSize[0], playerSize[1]), false)) {
                mainPlayer.setY(newPosition);
            }
        }
        if (keyLeft) {
            int newPosition = mainPlayer.getX() - speed;
            //if (gamePanel.checkNoBarrier(new int[] { newPosition, currLocation[1] }, mainPlayer.getDimensions(), false)) {
            if (gamePanel.checkNoBarrier(new Rectangle(newPosition, currLocation[1], playerSize[0], playerSize[1]), false)) {
                mainPlayer.setX(newPosition);
            }
        }
        if (keyRight) {
            int newPosition = mainPlayer.getX() + speed;
            //if (gamePanel.checkNoBarrier(new int[] { newPosition, currLocation[1] }, mainPlayer.getDimensions(), false)) {
            if (gamePanel.checkNoBarrier(new Rectangle(newPosition, currLocation[1], playerSize[0], playerSize[1]), false)) {
                mainPlayer.setX(newPosition);
            }
        }

        // Check if collision with enemy or pickup item
        gamePanel.checkCollision(false);
    }

    /**
     * Get function for speed.
     * @return  int equal to player's speed stat
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Get function for keyUp; keyUp indicates whether the UP key is pressed.
     * @return boolean equal to keyUp's current state
     */
    public boolean isKeyUp() { return keyUp; }

    /**
     * Get function for keyDown; keyDown indicates whether the DOWN key is pressed.
     * @return boolean equal to keyDown's current state
     */
    public boolean isKeyDown() { return keyDown; }

    /**
     * Get function for keyLeft; keyLeft indicates whether the LEFT key is pressed.
     * @return boolean equal to keyLeft's current state
     */
    public boolean isKeyLeft() { return keyLeft; }

    /**
     * Get function for keyRight; keyRight indicates whether the RIGHT key is pressed.
     * @return boolean equal to keyRight's current state
     */
    public boolean isKeyRight() { return keyRight; }
}
